package com.todoapp.exceptions;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
    public static final String MESSAGE = "Nincs ilyen felhasználó";

    public NoSuchUserException(Integer id){
        super(String.format("%s, akinek az id-ja:  %d",MESSAGE,id));
    }
}
