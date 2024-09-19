package com.example.todoproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToOne(mappedBy = "todoItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Details details;

    public TodoItem(Long id, String title, String description, Details details) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.details = details;
    }

    public TodoItem(String title, String description, Details details) {
        this.title = title;
        this.description = description;
        this.details = details;
    }
}
