package com.programmingu.todoapp.task.dto;

import lombok.Getter;

@Getter
public class TaskPatchDto {
    private long taskId;
    private String title;
    private int order;
    private boolean completed;

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
