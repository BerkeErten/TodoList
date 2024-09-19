package com.example.todoproject.repository;

import com.example.todoproject.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
}
