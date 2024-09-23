package com.example.todoproject.service.todoitem;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.request.AddTodoItemRequest;
import com.example.todoproject.request.UpdateTodoItemRequest;
import org.hibernate.sql.Update;

import java.util.List;

public interface ITodoItemService {
    TodoItem addTodoItem(AddTodoItemRequest todoItem);
    List<TodoItem> getAllTodoItems();
    TodoItem getTodoItemById(Long id);
    void deleteTodoItemById(Long id);
    void updateTodoItem(UpdateTodoItemRequest request, Long todoItemId);

    TodoItemDto convertToDto(TodoItem todoItem1);
}
