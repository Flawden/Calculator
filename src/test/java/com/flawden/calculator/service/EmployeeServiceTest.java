package com.flawden.calculator.service;

import com.flawden.calculator.exceptions.EmployeeNotFoundException;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryImpl employeerRepositoryMock;

    private EmployeeBookService employeerService;

    private List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        employeerService = new EmployeeBookService(employeerRepositoryMock);
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
    public void getEmployeeById() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        when(employeerService.getEmployeeById(1)).thenReturn(employees.get(1));
        Assertions.assertEquals(employees.get(1), employeerService.getEmployeeById(1));
    }

    @Test
    public void getEmployeeByIdWithException() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        EmployeeNotFoundException exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeerService.getEmployeeById(999));
        Assertions.assertEquals("Ошибка! Сотрудник с указанным id не существует.", exception.getMessage());
    }

    @Test
    public void salarySum() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(250000, employeerService.salarySum());
    }

    @Test
    public void minSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(15000, employeerService.minSalary());
    }

    @Test
    public void maxSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(45000, employeerService.maxSalary());
    }

    @Test
    public void averageSalary() {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(31250.0, employeerService.averageSalary());
    }

}
