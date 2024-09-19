package com.example.todoproject.service.details;

import com.example.todoproject.model.Details;
import com.example.todoproject.repository.DetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class DetailsService implements IDetailsService{
    private DetailsRepository detailsRepository;

    @Override
    public Details getDetailsById(Long id) {
        return null;
    }

    @Override
    public Details createDetails(Date deadline, String priority, String severity, Long todoItemId) {
        return null;
    }

    @Override
    public Details updateDetails(Long id, Date deadline, String priority, String severity) {
        return null;
    }

    @Override
    public void deleteDetails(Long id) {

    }


}
