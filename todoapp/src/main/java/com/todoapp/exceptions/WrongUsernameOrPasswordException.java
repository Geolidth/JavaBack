package com.todoapp.exceptions;

public class WrongUsernameOrPasswordException extends RuntimeException{
    public static final String MESSAGE = "Hibás felhasználónév/jelszó!";

    public WrongUsernameOrPasswordException() {
        super(MESSAGE);
    }
}
