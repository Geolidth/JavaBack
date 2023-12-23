package com.todoapp.services;

import com.todoapp.exceptions.NoSuchUserException;
import com.todoapp.models.dao.TodoUser;
import com.todoapp.models.dto.newUser;

public interface UserService {
    TodoUser getById(Integer id) throws NoSuchUserException;
    TodoUser save(newUser newuser);
}
