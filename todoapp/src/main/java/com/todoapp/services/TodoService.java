package com.todoapp.services;

import com.todoapp.exceptions.NoSuchTodoException;
import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodos();

    List<Todo> getTodos(Optional<Boolean> isDone, Optional<String> q);

    Todo getById(Integer id) throws NoSuchTodoException;

    Todo save(newTodo todo);

    Todo update(Integer id, UpdateTodo update) throws NoSuchTodoException;

    void delete(Integer id) throws NoSuchTodoException;
}
