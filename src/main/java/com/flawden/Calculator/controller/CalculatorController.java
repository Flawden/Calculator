package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private double minus(@RequestParam double a, @RequestParam double b) {
       return calculatorService.minus(a, b);
    }

    @GetMapping("/plus")
    private double plus(@PathVariable double a, @PathVariable double b) {
        return calculatorService.plus(a, b);
    }

    @GetMapping("/multiply")
    private double multiply(@PathVariable double a, @PathVariable double b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    private double divide(@PathVariable double a, @PathVariable double b) {
        return calculatorService.divide(a, b);
    }

}
