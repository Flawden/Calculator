package com.flawden.calculator.exceptions;

public class IncorrectSalaryCalculationException extends RuntimeException {

    public IncorrectSalaryCalculationException() {
    }

    public IncorrectSalaryCalculationException(String message) {
        super(message);
    }

}
