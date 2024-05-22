package com.flawden.Calculator.service;

import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepository;
import com.flawden.Calculator.repository.EmployeerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeerService {

    @Mock
    private EmployeerRepository employeerRepositoryMock;

    @InjectMocks
    private EmployeerRepositoryImpl employeerRepositoryImpl;

    private EmployeerService employeerService;

    private List<Employee> employees = new ArrayList<>();

    public EmployeerService(EmployeerService employeerService) {
        this.employeerService = employeerService;
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
        System.out.println(employeerService.salarySum());
        //return "Итоговые затраты на зарплату: " + employeesRepository.getEmployees().stream().mapToInt(employee -> (int) employee.getSalary()).sum() + " рублей.";
    }

}
