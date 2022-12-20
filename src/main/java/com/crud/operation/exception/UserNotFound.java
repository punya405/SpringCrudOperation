package com.crud.operation.exception;

import lombok.Data;

@Data
public class UserNotFound extends RuntimeException{

    private String message;

    public UserNotFound(String message) {
        super(message);
        this.message = message;
    }
}
