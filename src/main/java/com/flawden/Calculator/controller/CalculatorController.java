package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UnknownFormatConversionException;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping()
    private String homePage() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/minus")
    private String minus(@RequestParam double a, @RequestParam double b) {
       return calculatorService.minus(a, b);
    }

    @GetMapping("/plus")
    private String plus(@RequestParam double a, @RequestParam double b) {
        System.out.println(a);
        return calculatorService.plus(a, b);
    }

    @GetMapping("/multiply")
    private String multiply(@RequestParam double a, @RequestParam double b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    private String divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new ArithmeticException("Error! You can't divide by zero");
        }
        return calculatorService.divide(a, b);
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
