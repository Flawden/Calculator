package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping()
    public String homePage() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/minus")
    public String minus(@RequestParam double a, @RequestParam double b) {
        return a + " - " + b + " = " + calculatorService.minus(a, b);
    }

    @GetMapping("/plus")
    public String plus(@RequestParam double a, @RequestParam double b) {
        return a + " + " + b + " = " + calculatorService.plus(a, b);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam double a, @RequestParam double b) {
        return a + " * " + b + " = " + calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new ArithmeticException("Error! You can't divide by zero");
        }
        return a + " / " + b + " = " + calculatorService.divide(a, b);
    }

    @ExceptionHandler(ArithmeticException.class)
    private String handler(ArithmeticException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private String handler(MethodArgumentTypeMismatchException e) {
        return "Mistake! A number was expected at the entrance.";
    }

}