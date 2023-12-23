package com.todoapp.controllers;

import com.todoapp.models.dao.Todo;
import com.todoapp.models.dto.UpdateTodo;
import com.todoapp.models.dto.newTodo;
import com.todoapp.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private TodoService todoService;
    public ToDoController( TodoService todoService) {
        this.todoService=todoService;
    }
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(
            @RequestParam Optional<Boolean> isDone, @RequestParam Optional<String> q){
        List<Todo> todos=todoService.getTodos(isDone,q);
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Integer id){
        Todo todo= todoService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> save(@RequestHeader Integer userId,@RequestBody newTodo newTodo){
    Todo savedTodo=todoService.save(userId,newTodo);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> update(
            @PathVariable Integer todoId,
            @RequestHeader Integer userId,
            @RequestBody UpdateTodo update){

            Todo updatedTodo=todoService.update(userId,todoId, update);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);

    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> delete(@PathVariable Integer todoId, @RequestHeader Integer userId){
        todoService.delete(userId, todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
