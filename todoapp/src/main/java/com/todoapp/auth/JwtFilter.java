package com.todoapp.auth;

import com.todoapp.models.dao.TodoUser;
import com.todoapp.services.TokenService;
import com.todoapp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(isPublicEndpoint(request)){
            filterChain.doFilter(request,response);
            return;
        }
        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ") && auth.length() > 7) {
            String token = auth.substring(7);
            if(tokenService.isValid(token)){
                String username = tokenService.extractUsername(token);
                setSecurityContext(username);
            }

        }
        filterChain.doFilter(request,response);
    }

    public void setSecurityContext(String username) {
        try {
            UserDetails user = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
            if(SecurityContextHolder.getContext().getAuthentication() == null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(UsernameNotFoundException e){
        }
    }

    public boolean isPublicEndpoint(HttpServletRequest request) {
        if(request.getMethod().equals("POST") && request.getServletPath().equals("/users")) {
            return true;
        }
        if(request.getMethod().equals("POST") && request.getServletPath().equals("/login")) {
            return true;
        }
        return false;
    }
}
