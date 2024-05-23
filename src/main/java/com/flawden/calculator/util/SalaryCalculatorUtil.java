package com.flawden.calculator.util;

import com.flawden.calculator.exceptions.IncorrectSalaryCalculationException;

public class SalaryCalculatorUtil {

    public static double getIncreasedSalaryByPercentage(double salaryToIncrease, int percent) {
        if (percent <= 0) {
            throw new IncorrectSalaryCalculationException("Ошибка! В соответствии с требованиями отдела охраны труда зарплату уменьшать запрещено!");
        }

        return salaryToIncrease / 100 * (100 + percent);
    }

}
