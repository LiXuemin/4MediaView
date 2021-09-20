package com.lxm.textconverter.service.mapper;

import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.service.dto.TaskDTO;
import org.springframework.stereotype.Service;


@Service
public class TaskMapper {

    public TextConvertTask taskDTOToTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        return TextConvertTask.builder()
            .sourceURL(taskDTO.getSourceURL())
            .appCode(taskDTO.getAppCode())
            .callbackURL(taskDTO.getCallbackURL())
            .build();
    }
}
