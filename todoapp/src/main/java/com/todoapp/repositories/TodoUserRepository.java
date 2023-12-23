package com.todoapp.repositories;

import com.todoapp.models.dao.TodoUser;
import org.springframework.data.repository.CrudRepository;

public interface TodoUserRepository extends CrudRepository<TodoUser, Integer> {

}
