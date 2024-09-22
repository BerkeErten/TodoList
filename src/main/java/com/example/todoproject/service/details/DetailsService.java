package com.example.todoproject.service.details;

import com.example.todoproject.model.Details;
import com.example.todoproject.model.TodoItem;
import com.example.todoproject.repository.DetailsRepository;
import com.example.todoproject.request.AddTodoItemDetailsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class DetailsService implements IDetailsService{
    private DetailsRepository detailsRepository;

}
