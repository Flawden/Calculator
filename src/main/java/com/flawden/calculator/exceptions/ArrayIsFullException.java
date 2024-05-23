package com.flawden.calculator.exceptions;

public class ArrayIsFullException extends RuntimeException {

    public ArrayIsFullException() {
    }

    public ArrayIsFullException(String message) {
        super(message);
    }

}
