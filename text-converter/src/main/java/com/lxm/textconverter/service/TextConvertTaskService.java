package com.lxm.textconverter.service;

import com.lxm.textconverter.config.Constants;
import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.repository.TextConvertTaskRepository;
import com.lxm.textconverter.service.dto.TaskDTO;
import com.lxm.textconverter.service.mapper.TaskMapper;
import java.io.UnsupportedEncodingException;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TextConvertTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextConvertTaskService.class);
    private final TextConvertTaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final DefaultMQProducer defaultMQProducer;

    public TextConvertTaskService(TextConvertTaskRepository textConvertTaskRepository, TaskMapper taskMapper,
        DefaultMQProducer defaultMQProducer) {
        this.taskRepository = textConvertTaskRepository;
        this.taskMapper = taskMapper;
        this.defaultMQProducer = defaultMQProducer;
    }

    /**
     * save task to mysql && send to mq
     */
    public TextConvertTask addTask(TaskDTO taskDTO) {
        TextConvertTask task = taskMapper.taskDTOToTask(taskDTO);
        task.setConvertState(String.valueOf(Constants.CONVERT_STATE.NEW));
        try {
            Message msg = new Message(Constants.TOPIC_TEXT_CONVERT, "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            defaultMQProducer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    taskRepository.save(task);
                }

                @Override
                public void onException(Throwable e) {
                    LOGGER.error("AddTask error when send message to rocketmq : {}", e.getMessage(), e);
                }
            });
        } catch (UnsupportedEncodingException | InterruptedException | MQClientException | RemotingException e) {
            LOGGER.error("AddTask error when send message to rocketmq : {}", e.getMessage(), e);
        }
        return task;
    }
}
