package com.programmingu.todoapp.task.dto;

import lombok.Getter;

@Getter
public class TaskPatchDto {
    private long taskId;
    private String title;
    private int orders;
    private boolean completed;

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
