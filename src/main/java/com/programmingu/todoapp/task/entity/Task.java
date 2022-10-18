package com.programmingu.todoapp.task.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Task {
    @Id
    private long taskId;
    private String title;
    private int order;
    private boolean completed;
}
