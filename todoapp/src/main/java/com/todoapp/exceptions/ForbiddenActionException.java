package com.todoapp.exceptions;

public class ForbiddenActionException extends RuntimeException{
    public static final String MESSAGE = "Ehhez nincs jogod!";

    public ForbiddenActionException() {
        super(MESSAGE);
    }
}
