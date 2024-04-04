package com.flawden.Calculator.controller;

import com.flawden.Calculator.exceptions.IncorrectDepartmentNumber;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.service.EmployeeBookService;
import com.flawden.Calculator.util.Tester;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController("/api/v1/employee")
public class EmployeeBookController {

    private EmployeeBookService employeeBookService;

    public EmployeeBookController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        employeeBookService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void deleteEmployee(int id) {
        employeeBookService.deleteEmployee(id);
    }

    public ResponseEntity getEmployeeById(int id) {
        employeeBookService.getEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity salarySum() {
        employeeBookService.salarySum();
        ResponseEntity.ok().build();
    }

    public void salarySum(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double salarySum = 0;
        for (Employee employee : employeesInDepartment) {
            salarySum += employee.getSalary();
        }
        System.out.println("Итоговые затраты на зарплату в отделе номер " + department + ": " + salarySum + " рублей.");
    }

    public void minSalary() {
        double minSalary = Double.MAX_VALUE;
        for (Employee employee : employees) {
            if (minSalary > employee.getSalary()) {
                minSalary = employee.getSalary();
            }
        }
        System.out.println("Минимальная зарплата: " + minSalary + " рублей.");
    }

    public void minSalary(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double minSalary = Double.MAX_VALUE;
        for (Employee employee : employeesInDepartment) {
            if (minSalary > employee.getSalary()) {
                minSalary = employee.getSalary();
            }
        }
        System.out.println("Минимальная зарплата в отделе номер " + department + ": " + minSalary + " рублей.");
    }

    public void maxSalary() {
        double maxSalary = 0;
        for (Employee employee : employees) {
            if (maxSalary < employee.getSalary()) {
                maxSalary = employee.getSalary();
            }
        }
        System.out.println("Максимальная зарплата: " + maxSalary + " рублей.");
    }

    public void maxSalary(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double maxSalary = 0;
        for (Employee employee : employeesInDepartment) {
            if (maxSalary < employee.getSalary()) {
                maxSalary = employee.getSalary();
            }
        }
        System.out.println("Максимальная зарплата в отделе номер " + department + ": " + maxSalary + " рублей.");
    }

    public void averageSalary() {
        double salarySum = 0;
        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }
        System.out.println("Средняя зарплата сотрудников: " + (salarySum / employees.length) + " рублей.");
    }

    public void averageSalary(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double salarySum = 0;
        for (Employee employee : employeesInDepartment) {
            salarySum += employee.getSalary();
        }
        System.out.println("Средняя зарплата сотрудников в отделе номер " + department + ": " + (salarySum / employeesInDepartment.length) + " рублей.");
    }

    public void fullnamePrinter() {
        Arrays.stream(employees).forEach(employee -> System.out.println(employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname()));
    }

    public void salaryIncreaseInPercent(int percent) {
        if (percent == 0) {
            System.out.println("Зарплата сотрудников не была изменена");
            return;
        }
        if (percent < 0) {
            System.out.println("Отдел охраны труда запрещает уменьшать сотрудникам размер зароботной платы, во избежание снижения уровня эффективности сотрудников");
            return;
        }
        for (Employee employee : employees) {
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " до индексации.");
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " после индексации.");
        }
    }

    public void salaryIncreaseInPercent(int percent, int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        if (percent == 0) {
            System.out.println("Зарплата сотрудников не была изменена");
            return;
        }
        if (percent < 0) {
            System.out.println("Отдел охраны труда запрещает уменьшать сотрудникам размер зароботной платы, во избежание снижения уровня эффективности сотрудников");
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        System.out.println("Информация по индексации отдела номер " + department + ":");
        for (Employee employee : employeesInDepartment) {
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " до индексации.");
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " после индексации.");
        }
    }

    public Employee[] findEmployeesByDepartment(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return new Employee[0];
        }
        List<Employee> employeesByDepartment = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                employeesByDepartment.add(employee);
            }
        }
        return employeesByDepartment.toArray(new Employee[0]);
    }
}
