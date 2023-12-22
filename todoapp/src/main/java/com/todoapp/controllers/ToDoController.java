package com.todoapp.controllers;

import com.todoapp.models.Todo;
import com.todoapp.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {
    private TodoService todoService;
    public ToDoController( TodoService todoService) {
        this.todoService=todoService;
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){

        List<Todo> todos= todoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }
}
