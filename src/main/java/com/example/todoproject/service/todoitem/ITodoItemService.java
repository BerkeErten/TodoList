package com.example.todoproject.service.todoitem;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.request.AddTodoItemRequest;

import java.util.List;

public interface ITodoItemService {
    TodoItem addTodoItem(AddTodoItemRequest todoItem);
    List<TodoItem> getAllTodoItems();
    TodoItem getTodoItemById(Long id);
    void deleteTodoItemById(Long id);
    void updateTodoItem(TodoItem todoItem, Long todoItemId);

    TodoItemDto convertToDto(TodoItem todoItem1);
}
