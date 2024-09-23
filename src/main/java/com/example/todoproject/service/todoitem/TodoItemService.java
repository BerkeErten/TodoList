package com.example.todoproject.service.todoitem;

import com.example.todoproject.dto.TodoItemDto;
import com.example.todoproject.model.Details;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.repository.DetailsRepository;
import com.example.todoproject.repository.TodoItemRepository;
import com.example.todoproject.request.AddTodoItemRequest;
import com.example.todoproject.request.UpdateTodoItemRequest;
import jakarta.persistence.EntityNotFoundException;
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
        return todoItemRepository.findAll();
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
    public void updateTodoItem(UpdateTodoItemRequest request, Long todoItemId) {

        TodoItem item = todoItemRepository.findById(todoItemId)
                .orElseThrow(() -> new EntityNotFoundException("Todo item not found"));
        Details details = detailsRepository.findByTodoItem_Id(todoItemId);



        if (request.getTitle() != null) {
            item.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            item.setDescription(request.getDescription());
        }
        if (request.getDetails() != null) {
            details.setDeadline(request.getDetails().getDeadline() != null ? request.getDetails().getDeadline() : details.getDeadline());
            details.setPriority(request.getDetails().getPriority() != null ? request.getDetails().getPriority() : details.getPriority());
            details.setSeverity(request.getDetails().getSeverity() != null ? request.getDetails().getSeverity() : details.getSeverity());
        }


        todoItemRepository.save(item);
        detailsRepository.save(details);
    }

    @Override
    public TodoItemDto convertToDto(TodoItem todoItem) {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setId(todoItem.getId());
        todoItemDto.setTitle(todoItem.getTitle());
        todoItemDto.setDescription(todoItem.getDescription());

        if (todoItem.getDetails() != null) {
            todoItemDto.setDeadline(todoItem.getDetails().getDeadline());
            todoItemDto.setSeverity(todoItem.getDetails().getSeverity());
            todoItemDto.setPriority(todoItem.getDetails().getPriority());
        }


        return todoItemDto;
    }


}
