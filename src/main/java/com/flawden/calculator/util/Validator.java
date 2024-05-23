package com.flawden.calculator.util;

import com.flawden.calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.calculator.exceptions.IncorrectEmployeeException;
import com.flawden.calculator.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validator {

    private static final String NAME_REGEX = "^[A-Za-zА-Яа-я]+$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    public static void isValidDepartment(int department) throws IncorrectDepartmentNumberException {
        if (department < 0) {
            throw new IncorrectDepartmentNumberException("Ошибка! Номер департамента не может быть отрицательным");
        }
        if (department > 5 || department == 0) {
            throw new IncorrectDepartmentNumberException("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5");
        }

    }

    public static void isValidEmployee(Employee employee) throws IncorrectEmployeeException {
        if (!isValidName(employee.getFirstname()) ||
                !isValidName(employee.getLastname()) ||
                !isValidName(employee.getPatronymic())) {
            throw new IncorrectEmployeeException("Ошибка! Поле: имя, фамилия и(или) отчество " +
                    "не может содержать других символов, кроме кирилицы или латиницы. " +
                    "Спецсимволы и числа недопустимы. Даже если вы Петр 1");
        }
    }

    private static boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

}
