package com.example.todoproject.request;

import com.example.todoproject.model.Details;
import lombok.Data;

@Data
public class UpdateTodoItemRequest {
    private String title;
    private String description;
    private Details details;

}
