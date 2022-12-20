package com.crud.operation.exception;

import lombok.Data;

@Data
public class EmployeeNotFound  extends RuntimeException{

    private String message;

    public EmployeeNotFound(String message) {
        super(message);
        this.message = message;
    }
}
