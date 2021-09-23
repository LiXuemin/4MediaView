package com.lxm.textconverter.web.rest;

import com.lxm.textconverter.config.Constants;
import com.lxm.textconverter.domain.TextConvertTask;
import com.lxm.textconverter.repository.TextConvertTaskRepository;
import com.lxm.textconverter.service.TextConvertTaskService;
import com.lxm.textconverter.service.dto.TaskDTO;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * restful api to crud of task
 */
@RestController
@RequestMapping("/api/text")
public class TextConvertTaskController {

    private final TextConvertTaskService taskService;
    private final TextConvertTaskRepository taskRepository;

    public TextConvertTaskController(TextConvertTaskService taskService, TextConvertTaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    /**
     * add a text convert task
     */
    @PostMapping("/addTask")
    public ResponseEntity<TextConvertTask> addTask(@Valid @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.addTask(taskDTO));
    }

    /**
     * get a task info
     * @param taskId
     * @return
     * {@link ResponseEntity<TaskDTO>}
     */
    @GetMapping("/getTask")
    public ResponseEntity<TextConvertTask> getTask(Long taskId) {
        Optional<TextConvertTask> task = taskRepository.findById(taskId);
        if (!task.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find task.");
        }
        return ResponseEntity.ok(task.get());
    }

    /**
     * cancel the task
     */
    @PostMapping("/cancelTask")
    public ResponseEntity<TextConvertTask> cancelTask(Long taskId) {
        Optional<TextConvertTask> task = taskRepository.findById(taskId);
        if (!task.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find task.");
        }
        TextConvertTask convertTask = task.get();
        convertTask.setConvertState(String.valueOf(Constants.CONVERT_STATE.CANCELED));
        taskRepository.save(convertTask);
        return ResponseEntity.ok(convertTask);
    }

    @RequestMapping("/resetTask")
    public ResponseEntity<TextConvertTask> resetTask() {
        return ResponseEntity.ok(new TextConvertTask());
    }
}
