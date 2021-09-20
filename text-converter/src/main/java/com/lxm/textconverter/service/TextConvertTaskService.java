package com.lxm.textconverter.service;

import com.lxm.textconverter.config.Constants;
import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.repository.TextConvertTaskRepository;
import com.lxm.textconverter.service.dto.TaskDTO;
import com.lxm.textconverter.service.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TextConvertTaskService {

    private final TextConvertTaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TextConvertTaskService(TextConvertTaskRepository textConvertTaskRepository, TaskMapper taskMapper) {
        this.taskRepository = textConvertTaskRepository;
        this.taskMapper = taskMapper;
    }

    public TextConvertTask addTask(TaskDTO taskDTO) {
        TextConvertTask task = taskMapper.taskDTOToTask(taskDTO);
        task.setConvertState(String.valueOf(Constants.CONVERT_STATE.NEW));
        return taskRepository.save(task);
    }


}
