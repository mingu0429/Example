package com.programmingu.todoapp.task.dto;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class TaskResponseDto {
    private long taskId;
    private String title;
    private int order;
    private boolean completed;
}
