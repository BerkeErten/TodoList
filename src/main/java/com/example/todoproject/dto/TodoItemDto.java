package com.example.todoproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoItemDto {
    private long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean completed;
}
