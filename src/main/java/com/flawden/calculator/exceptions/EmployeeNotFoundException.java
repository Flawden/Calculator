package com.flawden.calculator.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

}