package com.flawden.Calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Double minus(double num1, double num2) {
        return num1 - num2;
    }

    public Double plus(double num1, double num2) {
        return num1 + num2;
    }

    public Double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public Double divide(double num1, double num2) {
        return num1 / num2;
    }


}
