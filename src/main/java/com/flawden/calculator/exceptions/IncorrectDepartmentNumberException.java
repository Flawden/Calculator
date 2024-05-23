package com.flawden.calculator.exceptions;

public class IncorrectDepartmentNumberException extends RuntimeException {

    public IncorrectDepartmentNumberException() {
    }

    public IncorrectDepartmentNumberException(String message) {
        super(message);
    }
}
