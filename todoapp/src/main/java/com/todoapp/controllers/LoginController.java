package com.todoapp.controllers;

import com.todoapp.models.dto.Login;
import com.todoapp.models.dto.Token;
import com.todoapp.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login login){
        return ResponseEntity.status(OK).body(loginService.login(login));
    }
}
