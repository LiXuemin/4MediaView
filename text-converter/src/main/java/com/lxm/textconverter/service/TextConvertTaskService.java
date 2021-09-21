package com.lxm.textconverter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxm.textconverter.config.Constants;
import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.repository.TextConvertTaskRepository;
import com.lxm.textconverter.service.dto.TaskDTO;
import com.lxm.textconverter.service.mapper.TaskMapper;
import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
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
    private final ObjectMapper jsonMapper;

    public TextConvertTaskService(TextConvertTaskRepository textConvertTaskRepository, TaskMapper taskMapper,
        DefaultMQProducer defaultMQProducer, ObjectMapper jsonMapper) {
        this.taskRepository = textConvertTaskRepository;
        this.taskMapper = taskMapper;
        this.defaultMQProducer = defaultMQProducer;
        this.jsonMapper = jsonMapper;
    }

    /**
     * save task to mysql && send to mq
     * TODO 保证数据库插入和消息发送的事务
     */
    public TextConvertTask addTask(TaskDTO taskDTO) {
        TextConvertTask task = taskMapper.taskDTOToTask(taskDTO);
        task.setConvertState(String.valueOf(Constants.CONVERT_STATE.NEW));
        taskRepository.save(task);
        try {
            Message msg = buildMsgOfTask(task);
            defaultMQProducer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    LOGGER.info("sendResult: {}", sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    LOGGER.error("AddTask error when send message to rocketmq : {}", e.getMessage(), e);
                }
            });
        } catch (InterruptedException | MQClientException | RemotingException | JsonProcessingException e) {
            LOGGER.error("AddTask error when send message to rocketmq : {}", e.getMessage(), e);
        }
        return task;
    }

    private Message buildMsgOfTask(TextConvertTask task) throws JsonProcessingException {
        return new Message(Constants.TOPIC_TEXT_CONVERT, jsonMapper.writeValueAsString(task).getBytes(StandardCharsets.UTF_8));
    }
}
