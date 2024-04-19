package com.flawden.Calculator.util;

import com.flawden.Calculator.exceptions.IncorrectDepartmentNumber;

public class Tester {

    public static boolean isValidDepartment(int department) throws IncorrectDepartmentNumber {
        if (department < 0) {
            throw new IncorrectDepartmentNumber("Ошибка! Номер департамента не может быть отрицательным");
        }
        if (department > 5 || department == 0) {
            throw new IncorrectDepartmentNumber("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5");
        }
        else {
            return true;
        }

    }

}
