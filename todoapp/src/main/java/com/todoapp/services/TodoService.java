package com.todoapp.services;

import com.todoapp.exceptions.NoSuchTodoException;
import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    Todo save(newTodo todo);

    Todo update(Integer id, UpdateTodo update) throws NoSuchTodoException;
}
