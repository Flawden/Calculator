package com.flawden.Calculator.controller;

import com.flawden.Calculator.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorServiceParametrisedTest {

    private CalculatorService calculatorService = new CalculatorService();

    public static Stream<Arguments> provideParamsForPlusTest(){
        return Stream.of(
                Arguments.of(5,5,10),
                Arguments.of(15,5,20),
                Arguments.of(2,2,4),
                Arguments.of(11,23,34),
                Arguments.of(15,10,25),
                Arguments.of(150,150,300)
        );
    }

    public static Stream<Arguments> provideParamsForMinusTest(){
        return Stream.of(
                Arguments.of(5,5,0),
                Arguments.of(15,5,10),
                Arguments.of(2,2,0),
                Arguments.of(11,23,-12),
                Arguments.of(15,10,5),
                Arguments.of(150,150,0)
        );
    }

    public static Stream<Arguments> provideParamsForDivideTest(){
        return Stream.of(
                Arguments.of(5,5,1),
                Arguments.of(15,5,3),
                Arguments.of(2,2,1),
                Arguments.of(11,22,0.5),
                Arguments.of(15,10,1.5),
                Arguments.of(150,150,1)
        );
    }

    public static Stream<Arguments> provideParamsForMultipleTest(){
        return Stream.of(
                Arguments.of(5,5,25),
                Arguments.of(15,5,75),
                Arguments.of(2,2,4),
                Arguments.of(11,23,253),
                Arguments.of(15,10,150),
                Arguments.of(150,150,22500)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForPlusTest")
    public void plus(double num1, double num2, double result) {
        Assertions.assertEquals(result, calculatorService.plus(num1, num2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinusTest")
    public void minus(double num1, double num2, double result) {
        Assertions.assertEquals(result, calculatorService.minus(num1, num2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMultipleTest")
    public void multiply(double num1, double num2, double result) {
        Assertions.assertEquals(result, calculatorService.multiply(num1, num2));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDivideTest")
    public void divide(double num1, double num2, double result) {
        Assertions.assertEquals(result, calculatorService.divide(num1, num2));
    }

}
