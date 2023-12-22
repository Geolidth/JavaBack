package com.todoapp.services;

import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.newTodo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    Todo save(newTodo todo);
}
