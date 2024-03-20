package com.flawden.Calculator.service;

import com.flawden.Calculator.model.Calculator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class CalculatorService {

    public double minus(double a, double b) {
        return Calculator.minus(a, b);
    }

    public double plus(double a, double b) {
        return Calculator.plus(a, b);
    }

    public double multiply(double a, double b) {
        return Calculator.multiply(a, b);
    }

    public double divide(double a, double b) {
        return Calculator.divide(a, b);
    }


}
