package com.flawden.calculator.service;

import com.flawden.calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.calculator.model.Employee;
import com.flawden.calculator.repository.EmployeeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeRepositoryImpl employeerRepositoryMock;

    private DepartmentService departmentService;

    private List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentService(employeerRepositoryMock);
    }

    @BeforeEach
    public void reposInit() {
        employees.add(new Employee("Firsname", "Patronymic", "Lastname", 1, 30000));
        employees.add(new Employee("Oleg", "Valerievich", "Sazanov", 2, 45000));
        employees.add(new Employee("Mavic", "Gavic", "Qavic", 1, 35000));
        employees.add(new Employee("One", "More", "Worker", 3, 15000));
        employees.add(new Employee("Another", "One", "Worker", 1, 30000));
        employees.add(new Employee("Something", "Else", "Sazanov", 2, 45000));
        employees.add(new Employee("Mavic", "Gavic", "Qavic", 1, 35000));
        employees.add(new Employee("Lad", "Pad", "Bad", 3, 15000));
    }

    @Test
    public void salarySum() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(130000, departmentService.salarySum(1));
    }

    @Test
    public void salarySumWithExceptions() {
        IncorrectDepartmentNumberException exceptionDepartmentIsNegative = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.salarySum(-1));
        IncorrectDepartmentNumberException exceptionDepartmentIsIncorrect = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.salarySum(6));
        Assertions.assertEquals("Ошибка! Номер департамента не может быть отрицательным", exceptionDepartmentIsNegative.getMessage());
        Assertions.assertEquals("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5", exceptionDepartmentIsIncorrect.getMessage());
    }

    @Test
    public void minSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(30000, departmentService.minSalary(1));
    }

    @Test
    public void minSalaryWithExceptions() {
        IncorrectDepartmentNumberException exceptionDepartmentIsNegative = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.minSalary(-1));
        IncorrectDepartmentNumberException exceptionDepartmentIsIncorrect = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.minSalary(6));
        Assertions.assertEquals("Ошибка! Номер департамента не может быть отрицательным", exceptionDepartmentIsNegative.getMessage());
        Assertions.assertEquals("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5", exceptionDepartmentIsIncorrect.getMessage());
    }

    @Test
    public void maxSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(35000, departmentService.maxSalary(1));
    }

    @Test
    public void maxSalaryWithExceptions() {
        IncorrectDepartmentNumberException exceptionDepartmentIsNegative = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.maxSalary(-1));
        IncorrectDepartmentNumberException exceptionDepartmentIsIncorrect = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.maxSalary(6));
        Assertions.assertEquals("Ошибка! Номер департамента не может быть отрицательным", exceptionDepartmentIsNegative.getMessage());
        Assertions.assertEquals("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5", exceptionDepartmentIsIncorrect.getMessage());
    }

    @Test
    public void averageSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(32500.0, departmentService.averageSalary(1));
    }

    @Test
    public void averageSalaryWithExceptions() {
        IncorrectDepartmentNumberException exceptionDepartmentIsNegative = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.averageSalary(-1));
        IncorrectDepartmentNumberException exceptionDepartmentIsIncorrect = Assertions.assertThrows(IncorrectDepartmentNumberException.class, () -> departmentService.averageSalary(6));
        Assertions.assertEquals("Ошибка! Номер департамента не может быть отрицательным", exceptionDepartmentIsNegative.getMessage());
        Assertions.assertEquals("Ошибка! Номера департаментов начинаются с 1 и заканчиваются 5", exceptionDepartmentIsIncorrect.getMessage());
    }

}
