package com.todoapp.services;

import com.todoapp.exceptions.WrongUsernameOrPasswordException;
import com.todoapp.models.dto.Login;
import com.todoapp.models.dto.Token;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;
    @Override
    public Token login(Login login) throws WrongUsernameOrPasswordException {
        try {
            UserDetails user = userService.loadUserByUsername(login.getUsername());
            if(passwordEncoder.matches(login.getPassword(), user.getPassword())){
                return new Token(tokenService.generateToken(user));
            }else{
                throw new WrongUsernameOrPasswordException();
            }
        } catch(UsernameNotFoundException e){
            throw new WrongUsernameOrPasswordException();
        }
    }

}
