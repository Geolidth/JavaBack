package com.todoapp.services;

import com.todoapp.exceptions.WrongUsernameOrPasswordException;
import com.todoapp.models.dto.Login;
import com.todoapp.models.dto.Token;

public interface LoginService {
    Token login(Login login) throws WrongUsernameOrPasswordException;
}
