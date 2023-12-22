package com.todoapp.models.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String title;
    private boolean isDone;

    public Todo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public Todo() {

    }

}
