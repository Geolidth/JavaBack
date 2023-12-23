package com.todoapp.services;

import com.todoapp.exceptions.NoSuchUserException;
import com.todoapp.models.dao.TodoUser;
import com.todoapp.models.dto.newUser;
import com.todoapp.repositories.TodoUserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private TodoUserRepository userRepository;
    @Override
    public TodoUser getById(Integer id) throws NoSuchUserException {
        return userRepository.findById(id).orElseThrow(()->new NoSuchUserException(id));
    }

    @Override
    public TodoUser save(newUser newuser) {
        return userRepository.save(convertToUser(newuser));
    }

    public TodoUser convertToUser(newUser newuser) {
        TodoUser user = new TodoUser();
        user.setUsername(newuser.getUserName());
        user.setPassword(newuser.getPassword()); //TODO: encode password!!!
        return user;
    }


}
