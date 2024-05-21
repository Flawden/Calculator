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
    public String minus(@RequestParam double num1, @RequestParam double num2) {
        return num1 + " - " + num2 + " = " + calculatorService.minus(num1, num2);
    }

    @GetMapping("/plus")
    public String plus(@RequestParam double num1, @RequestParam double num2) {
        return num1 + " + " + num2 + " = " + calculatorService.plus(num1, num2);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam double num1, @RequestParam double num2) {
        return num1 + " * " + num2 + " = " + calculatorService.multiply(num1, num2);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam double num1, @RequestParam double num2) {
        return num1 + " / " + num2 + " = " + calculatorService.divide(num1, num2);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private String handler(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private String handler(MethodArgumentTypeMismatchException e) {
        return "Mistake! A number was expected at the entrance.";
    }

}