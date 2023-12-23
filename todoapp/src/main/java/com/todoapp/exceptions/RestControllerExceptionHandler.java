package com.todoapp.exceptions;

import com.todoapp.models.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestExceptions(MissingRequestHeaderException e){
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
    }
    @ExceptionHandler(ForbiddenActionException.class)
    public ResponseEntity<ErrorMessage> handleForbiddenExceptions(ForbiddenActionException e){
        return ResponseEntity.status(FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
    }

}
