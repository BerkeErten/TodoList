package com.example.todoproject.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddTodoItemDetailsRequest {
    private LocalDate deadline;
    private String priority;
    private String severity;

}
