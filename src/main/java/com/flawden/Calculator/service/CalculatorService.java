package com.flawden.Calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Double minus(double a, double b) {
        return a - b;
    }

    public Double plus(double a, double b) {
        return a + b;
    }

    public Double multiply(double a, double b) {
        return a * b;
    }

    public Double divide(double a, double b) {
        return a / b;
    }


}
