package com.flawden.Calculator.util;

import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.exceptions.IncorrectEmployeeException;
import com.flawden.Calculator.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Tester {

    public static boolean isValidDepartment(int department) throws IncorrectDepartmentNumberException {
        if (department < 0) {
            throw new IncorrectDepartmentNumberException("Ошибка! Номер департамента не может быть отрицательным");
        }
        if (department > 5 || department == 0) {
            throw new IncorrectDepartmentNumberException("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5");
        }
        else {
            return true;
        }

    }

    public static boolean isValidEmployee(Employee employee) throws IncorrectEmployeeException {
        if((StringUtils.isEmpty(employee.getFirstname())) ||
                (StringUtils.isEmpty(employee.getLastname())) ||
                (StringUtils.isEmpty(employee.getPatronymic()))) {
            throw new IncorrectEmployeeException("Ошибка! Поля: имя, фамилия и(или) отчество не может быть пустым");
        }
        String regex = "^[A-Za-zА-Яа-я]+";
        if(!((Pattern.matches(regex, employee.getFirstname())) ||
                (Pattern.matches(regex, employee.getLastname())) ||
                (Pattern.matches(regex, employee.getPatronymic())))) {
            throw new IncorrectEmployeeException("Ошибка! Поля: имя, фамилия и(или) отчество не может содержать других символов, кроме кирилицы или латиницы. Спецсимволы и числа недопустимы. Даже если вы Петр 1");
        }
        return true;
    }

}
