package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

@SpringBootTest
public class CalculatorControllerTest {

    private CalculatorController calculatorController = new CalculatorController(new CalculatorService());

    @Test
    public void homePage() {
        Assertions.assertEquals("Добро пожаловать в калькулятор", calculatorController.homePage());
        Assertions.assertNotEquals("Добро пожаловать в Казахстан", calculatorController.homePage());
    }

    @Test
    public void minus() {
        Assertions.assertEquals("5.0 - 5.0 = 0.0", calculatorController.minus(5, 5));
        Assertions.assertEquals("3.0 - 5.0 = -2.0", calculatorController.minus(3, 5));
    }

    @Test
    public void plus() {
        Assertions.assertEquals("5.0 + 5.0 = 10.0", calculatorController.plus(5, 5));
        Assertions.assertEquals("-5.0 + 15.0 = 10.0", calculatorController.plus(-5, 15));
    }

    @Test
    public void multiply() {
        Assertions.assertEquals("5.0 * 5.0 = 25.0", calculatorController.multiply(5, 5));
        Assertions.assertEquals("5.0 * 0.0 = 0.0", calculatorController.multiply(5, 0));
    }

    @Test
    public void divide() {
        Assertions.assertEquals("5.0 / 5.0 = 1.0", calculatorController.divide(5, 5));
        Assertions.assertEquals("-5.0 / 8.0 = -0.625", calculatorController.divide(-5, 8));
    }

    @Test
    public void divideByZero() {
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            calculatorController.divide(5, 0);
        });
        Assertions.assertEquals("Error! You can't divide by zero", exception.getMessage());
    }

}