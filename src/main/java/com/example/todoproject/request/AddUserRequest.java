package com.example.todoproject.request;

import lombok.Data;


@Data
public class AddUserRequest {
    private String name;
    private String surname;
}
