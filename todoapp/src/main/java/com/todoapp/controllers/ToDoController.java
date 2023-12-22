package com.todoapp.controllers;

import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.newTodo;
import com.todoapp.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private TodoService todoService;
    public ToDoController( TodoService todoService) {
        this.todoService=todoService;
    }
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        List<Todo> todos= todoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> save(@RequestBody newTodo newTodo){
    Todo savedTodo=todoService.save(newTodo);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }
}
