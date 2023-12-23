package com.todoapp.services;

import com.todoapp.exceptions.ForbiddenActionException;
import com.todoapp.exceptions.NoSuchTodoException;
import com.todoapp.exceptions.NoSuchUserException;
import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodos();

    List<Todo> getTodos(Optional<Boolean> isDone, Optional<String> q);

    Todo getById(Integer id) throws NoSuchTodoException;

    Todo save(Integer userId,newTodo todo) throws NoSuchUserException;

    Todo update(Integer userId, Integer todoId, UpdateTodo update)
            throws NoSuchUserException, NoSuchTodoException, ForbiddenActionException;

    void delete(Integer userId, Integer todoId)
            throws NoSuchUserException, NoSuchTodoException, ForbiddenActionException;
}
