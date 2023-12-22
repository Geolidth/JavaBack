package com.todoapp.services;

import com.todoapp.models.Todo;
import com.todoapp.models.newTodo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    Todo save(newTodo todo);
}
