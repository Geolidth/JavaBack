package com.todoapp.controllers;

import com.todoapp.models.dao.TodoUser;
import com.todoapp.models.dto.newUser;
import com.todoapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<TodoUser> getById(@PathVariable Integer id) {
        TodoUser user = userService.getById(id);
        return ResponseEntity.status(OK).body(user);
    }
    @PostMapping
    public ResponseEntity<TodoUser> createUser(@RequestBody newUser newuser){
        return ResponseEntity.status(CREATED).body(userService.save(newuser));
    }
}
