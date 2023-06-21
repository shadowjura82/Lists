package com.example.list.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}
