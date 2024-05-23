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
    public void salarySum() throws IncorrectDepartmentNumberException {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(130000, departmentService.salarySum(1));
    }

    @Test
    public void minSalary() throws IncorrectDepartmentNumberException {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(30000, departmentService.minSalary(1));
    }

    @Test
    public void maxSalary() throws IncorrectDepartmentNumberException {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(35000, departmentService.maxSalary(1));
    }

    @Test
    public void averageSalary() throws IncorrectDepartmentNumberException {
        when(employeerRepositoryMock.getEmployees()).thenReturn(employees);
        Assertions.assertEquals(32500.0, departmentService.averageSalary(1));
    }

}
