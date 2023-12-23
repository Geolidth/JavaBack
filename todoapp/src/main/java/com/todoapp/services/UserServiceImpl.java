package com.todoapp.services;

import com.todoapp.exceptions.NoSuchUserException;
import com.todoapp.models.dao.TodoUser;
import com.todoapp.models.dto.newUser;
import com.todoapp.repositories.TodoUserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private TodoUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public TodoUser getById(Integer id) throws NoSuchUserException {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id));
    }

    @Override
    public TodoUser save(newUser newuser) {
        return userRepository.save(convertToUser(newuser));
    }

    public TodoUser convertToUser(newUser newuser) {
        TodoUser user = new TodoUser();
        user.setUsername(newuser.getUserName());
        user.setPassword(passwordEncoder.encode(newuser.getPassword()));
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(NoSuchUserException.MESSAGE));
    }
}
