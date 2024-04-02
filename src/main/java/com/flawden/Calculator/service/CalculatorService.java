package com.flawden.Calculator.service;

import com.flawden.Calculator.model.Calculator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class CalculatorService {

    public String minus(double a, double b) {
        double result = Calculator.minus(a, b);
        return a + " - " + b + " = " + result;
    }

    public String plus(double a, double b) {
        double result = Calculator.plus(a, b);
        return a + " + " + b + " = " + result;
    }

    public String multiply(double a, double b) {
        double result = Calculator.multiply(a, b);
        return a + " * " + b + " = " + result;
    }

    public String divide(double a, double b) {
        double result = Calculator.divide(a, b);
        return a + " / " + b + " = " + result;
    }


}
