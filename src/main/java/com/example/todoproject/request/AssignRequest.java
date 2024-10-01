package com.example.todoproject.request;

import lombok.Data;

@Data
public class AssignRequest {
    private Long todoitemid;
    private Long userid;
}
