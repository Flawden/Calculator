package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private CalculatorService calculatorService = new CalculatorService();

    @Test
    public void plus() {
        Assertions.assertEquals(10, calculatorService.plus(5, 5));
        Assertions.assertEquals(4, calculatorService.plus(3, 1));
    }

    @Test
    public void minus() {
        Assertions.assertEquals(0, calculatorService.minus(5, 5));
        Assertions.assertEquals(10, calculatorService.minus(15, 5));
    }

    @Test
    public void multiply() {
        Assertions.assertEquals(25, calculatorService.multiply(5, 5));
        Assertions.assertEquals(50, calculatorService.multiply(5, 10));
    }

    @Test
    public void divide() {
        Assertions.assertEquals(1, calculatorService.divide(5, 5));
        Assertions.assertEquals(5, calculatorService.divide(25, 5));
    }

    @Test
    public void divideByZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(5, 0);
        });
        Assertions.assertEquals("Error! You can't divide by zero", exception.getMessage());
    }

}
