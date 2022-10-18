package com.programmingu.todoapp.task.controller;

import com.programmingu.todoapp.task.dto.TaskPatchDto;
import com.programmingu.todoapp.task.dto.TaskPostDto;
import com.programmingu.todoapp.task.dto.TaskResponseDto;
import com.programmingu.todoapp.task.entity.Task;
import com.programmingu.todoapp.task.mapstruct.mapper.TaskMapper;
import com.programmingu.todoapp.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Validated
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    public TaskController(TaskService taskService, TaskMapper mapper){
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTask(@Valid @RequestBody TaskPostDto taskDto){
        Task task = mapper.taskPostDtoToTask(taskDto);
        Task response = taskService.createTask(task);
        return new ResponseEntity<>(mapper.taskToTaskResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{task-id}")
    public ResponseEntity patchTask(@PathVariable("task-id") @Positive long taskId,
                                    @Valid @RequestBody TaskPatchDto taskPatchDto){
        taskPatchDto.setTaskId(taskId);
        Task response = taskService.updateTask(mapper.taskPatchDtoToTask(taskPatchDto));
        return new ResponseEntity<>(mapper.taskToTaskResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{task-id}")
    public ResponseEntity getTask(@PathVariable("task-id") @Positive long taskId){
        Task response = taskService.findTask(taskId);
        return new ResponseEntity<>(mapper.taskToTaskResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTasks() {
        List<Task> tasks = taskService.findTasks();
        List<TaskResponseDto> response = mapper.tasksToTaskResponseDtos(tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity deleteTask(@PathVariable("task-id") @Positive long taskId) {
        System.out.println("# delete task");
        taskService.deleteTask(taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
