package com.todoapp.models;

public class Todo {

    private String title;
    private boolean isDone;

    public Todo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }
}
