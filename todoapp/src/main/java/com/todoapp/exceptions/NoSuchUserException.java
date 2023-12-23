package com.todoapp.exceptions;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
    public static final String MESSAGE = "Nincs felhasználó ilyen id-val";

    public NoSuchUserException(Integer id){
        super(String.format("%s: %d",MESSAGE,id));
    }
}
