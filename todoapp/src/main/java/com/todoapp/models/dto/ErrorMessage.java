package com.todoapp.models.dto;

import lombok.Data;

@Data
public class ErrorMessage {
    private String status;
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
        status="error";
    }
}
