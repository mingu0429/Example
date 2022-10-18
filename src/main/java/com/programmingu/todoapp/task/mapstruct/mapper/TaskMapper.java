package com.programmingu.todoapp.task.mapstruct.mapper;

import com.programmingu.todoapp.task.dto.TaskPatchDto;
import com.programmingu.todoapp.task.dto.TaskPostDto;
import com.programmingu.todoapp.task.dto.TaskResponseDto;
import com.programmingu.todoapp.task.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task taskPostDtoToTask(TaskPostDto taskPostDto);
    Task taskPatchDtoToTask(TaskPatchDto taskPatchDto);
    TaskResponseDto taskToTaskResponseDto(Task task);
    List<TaskResponseDto> tasksToTaskResponseDtos(List<Task>tasks);
}
