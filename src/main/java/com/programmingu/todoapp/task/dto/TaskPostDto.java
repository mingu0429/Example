package com.programmingu.todoapp.task.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class TaskPostDto {
    @NotBlank(message = "공백이 아니어야 합니다.")
    private String title;
    private int order;
    private boolean completed;

}
