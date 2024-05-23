package com.flawden.calculator.exceptions;

public class IncorrectEmployeeException extends RuntimeException {

    public IncorrectEmployeeException() {
    }

    public IncorrectEmployeeException(String message) {
        super(message);
    }

}
