package com.lxm.textconverter.service.mapper;

import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.service.dto.TaskDTO;
import java.time.Instant;
import org.springframework.stereotype.Service;


@Service
public class TaskMapper {

    public TextConvertTask taskDTOToTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        return TextConvertTask.builder()
            .sourceURL(taskDTO.getSourceURL())
            .callbackURL(taskDTO.getCallbackURL())
            .createdBy(taskDTO.getCreatedBy())
            .createdDate(Instant.now())
            .build();
    }
}
