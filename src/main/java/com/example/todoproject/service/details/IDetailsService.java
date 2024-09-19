package com.example.todoproject.service.details;

import com.example.todoproject.model.Details;

import java.util.Date;

public interface IDetailsService {
    Details getDetailsById(Long id);
    Details createDetails(Date deadline, String priority, String severity, Long todoItemId);
    Details updateDetails(Long id, Date deadline, String priority, String severity);
    void deleteDetails(Long id);


}
