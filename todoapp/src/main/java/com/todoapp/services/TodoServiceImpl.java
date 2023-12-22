package com.todoapp.services;

import com.todoapp.models.Todo;
import com.todoapp.models.newTodo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private List<Todo> todos;
    public TodoServiceImpl() {
        todos=new ArrayList<>();
        todos.add(new Todo(1,"Főzni",false));
        todos.add(new Todo(2,"Edzeni",false));
        todos.add(new Todo(3,"Alapozás",true));
    }
    @Override
    public List<Todo> getTodos() {
        return todos;
    }
    @Override
    public Todo save(newTodo newTodo) {
        Todo todo= new Todo(todos.size()+1,newTodo.getTitle(),false);
        todos.add(todo);
        return todo;
    }
}
