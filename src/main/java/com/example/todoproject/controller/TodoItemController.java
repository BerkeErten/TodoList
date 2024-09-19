package com.example.todoproject.controller;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.request.AddTodoItemRequest;
import com.example.todoproject.response.ApiResponse;
import com.example.todoproject.service.todoitem.ITodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/todoitems")
public class TodoItemController {
    private final ITodoItemService todoItemService;

    @GetMapping("todoitem/{todoitemId}")
    public ResponseEntity<ApiResponse> getTodoItemById(@PathVariable Long todoitemId){
        try {
            TodoItem todoitem = todoItemService.getTodoItemById(todoitemId);

            TodoItemDto todoItemDto = todoItemService.convertToDto(todoitem);

            return ResponseEntity.ok(new ApiResponse("Todo item retrieved successfully", todoItemDto));
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse("Failed to retrieve todo item: " + e.getMessage(),null);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveTodoItems(@RequestBody AddTodoItemRequest todoItemRequest) {
        try {
            TodoItem todoItem = todoItemService.addTodoItem(todoItemRequest);

            TodoItemDto todoItemDto = todoItemService.convertToDto(todoItem);

            ApiResponse response = new ApiResponse( "Todo item added successfully", todoItemDto);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // In case of any exceptions, create an error response
            ApiResponse errorResponse = new ApiResponse( "Failed to add todo item: " + e.getMessage(),null);

            // Return the response entity with HTTP 500 status
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
