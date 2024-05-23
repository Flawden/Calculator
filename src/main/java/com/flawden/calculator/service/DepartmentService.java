package com.flawden.calculator.service;

import com.flawden.calculator.model.Employee;
import com.flawden.calculator.repository.EmployeeRepository;
import com.flawden.calculator.util.SalaryCalculatorUtil;
import com.flawden.calculator.util.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    private final EmployeeRepository employeeRepository;

    public DepartmentService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public double salarySum(int department) {
        Validator.isValidDepartment(department);
        return Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToDouble(Employee::getSalary).sum();
    }

    public double minSalary(int department) {
        Validator.isValidDepartment(department);
        return Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToDouble(Employee::getSalary).min().getAsDouble();
    }

    public double maxSalary(int department) {
        Validator.isValidDepartment(department);
        return Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToDouble(Employee::getSalary).max().getAsDouble();
    }

    public double averageSalary(int department) {
        Validator.isValidDepartment(department);
        return Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToDouble(Employee::getSalary).average().getAsDouble();
    }

    public void salaryIncreaseInPercent(int percent, int department) {
        Validator.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        for (Employee employee : employeesInDepartment) {
            employee.setSalary(SalaryCalculatorUtil.getIncreasedSalaryByPercentage(employee.getSalary(), percent));
        }
    }

    public Employee[] findEmployeesByDepartment(int department) {
        Validator.isValidDepartment(department);
        List<Employee> employeesByDepartment = new ArrayList<>();
        for (Employee employee : employeeRepository.getEmployees()) {
            if (employee.getDepartment() == department) {
                employeesByDepartment.add(employee);
            }
        }
        return employeesByDepartment.toArray(new Employee[0]);
    }

    public String employeesPrinter(int department) {
        Validator.isValidDepartment(department);
        StringBuilder employeesInDepartmentAnswer = new StringBuilder();
        employeesInDepartmentAnswer
                .append("В отделе номер ")
                .append(department).append(" были найдены следующие сотрудники:");
        return Arrays.stream(findEmployeesByDepartment(department)).toList().stream()
                .map(employee -> employeesInDepartmentAnswer
                        .append("Сотрудник:\n" + "id: ")
                        .append(employee.getId()).append("\n")
                        .append("Имя: ").append(employee.getFirstname())
                        .append("\n").append("Фамилия: ")
                        .append(employee.getLastname())
                        .append("\n")
                        .append("Отчество: ")
                        .append(employee.getPatronymic())
                        .append("\n").append("Зароботная плата: ")
                        .append(employee.getSalary())
                        .append(" рублей")).toList().toString();
    }

}
