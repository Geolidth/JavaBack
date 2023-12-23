package com.todoapp.repositories;

import com.todoapp.models.dao.TodoUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TodoUserRepository extends CrudRepository<TodoUser, Integer> {
    Optional<TodoUser> findByUsername(String username);
}
