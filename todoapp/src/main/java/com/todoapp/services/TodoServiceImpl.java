package com.todoapp.services;

import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.newTodo;
import com.todoapp.repositories.TodoRepositroy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepositroy todoRepositroy;
    public TodoServiceImpl(TodoRepositroy todoRepositroy) {
        this.todoRepositroy=todoRepositroy;
    }
    @Override
    public List<Todo> getTodos() {
        return todoRepositroy.findAll();
    }
    @Override
    public Todo save(newTodo newTodo) {
        Todo todo= new Todo(newTodo.getTitle(), false);
        return todoRepositroy.save(todo);
    }
}
