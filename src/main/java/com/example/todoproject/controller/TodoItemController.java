package com.example.todoproject.controller;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.request.AddTodoItemRequest;
import com.example.todoproject.request.UpdateTodoItemRequest;
import com.example.todoproject.response.ApiResponse;
import com.example.todoproject.service.todoitem.ITodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            ApiResponse errorResponse = new ApiResponse( "Failed to add todo item: " + e.getMessage(),null);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getTodoItems() {
        try {
            List<TodoItem> todoItems = todoItemService.getAllTodoItems();

            List<TodoItemDto> todoItemDtos = todoItems.stream()
                    .map(todoItem -> todoItemService.convertToDto(todoItem))
                    .collect(Collectors.toList());

            ApiResponse response = new ApiResponse("Fetched all todo items", todoItemDtos);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse("Failed to fetch todo items: " + e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update/{todoitemId}")
    public ResponseEntity<ApiResponse> updateTodoItemById(@PathVariable Long todoitemId, @RequestBody UpdateTodoItemRequest request){
        try{

            todoItemService.updateTodoItem(request,todoitemId);
            TodoItemDto todoItemDto = todoItemService.convertToDto(todoItemService.getTodoItemById(todoitemId));
            ApiResponse response = new ApiResponse("Updated todo item",todoItemDto);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to update todo item", null);
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{todoitemId}")
    public ResponseEntity<ApiResponse> deleteTodoItemById(@PathVariable Long todoitemId ){
        try{
            todoItemService.deleteTodoItemById(todoitemId);

            ApiResponse response = new ApiResponse("Todo item deleted succesfully",null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(Exception e ){
            ApiResponse errorResponse = new ApiResponse("Failed to delete",null);
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
