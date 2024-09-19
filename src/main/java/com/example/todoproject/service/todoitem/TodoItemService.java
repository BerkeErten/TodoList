package com.example.todoproject.service.todoitem;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.Details;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.repository.DetailsRepository;
import com.example.todoproject.repository.TodoItemRepository;
import com.example.todoproject.request.AddTodoItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoItemService implements ITodoItemService{
    private final TodoItemRepository todoItemRepository;
    private final DetailsRepository detailsRepository;

    @Override
    public TodoItem addTodoItem(AddTodoItemRequest request) {
        Details details = request.getDetails();

        if (details != null) {
            details.setTodoItem(null);
        }

        TodoItem todoItem = createTodoItem(request);

        if (details != null) {
            details.setTodoItem(todoItem);
        }

        return todoItemRepository.save(todoItem);
    }

    private TodoItem createTodoItem(AddTodoItemRequest request){
        return new TodoItem(
                request.getTitle(),
                request.getDescription(),
                request.getDetails()
        );
    }

    @Override
    public List<TodoItem> getAllTodoItems() {
        return null;
    }

    @Override
    public TodoItem getTodoItemById(Long id) {
        return todoItemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTodoItemById(Long id) {
        todoItemRepository.findById(id).ifPresent(todoItemRepository::delete);
    }

    @Override
    public void updateTodoItem(TodoItem todoItem, Long todoItemId) {

    }

    @Override
    public TodoItemDto convertToDto(TodoItem todoItem) {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setId(todoItem.getId());
        todoItemDto.setTitle(todoItem.getTitle());
        todoItemDto.setDescription(todoItem.getDescription());

        if (todoItem.getDetails() != null) {
            todoItemDto.setDeadline(todoItem.getDetails().getDeadline());
        }


        return todoItemDto;
    }


}
