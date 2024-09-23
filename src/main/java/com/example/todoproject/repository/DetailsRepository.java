package com.example.todoproject.repository;

import com.example.todoproject.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details,Long> {
    Details findByTodoItem_Id(Long id);
}
