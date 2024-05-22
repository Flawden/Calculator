package com.flawden.Calculator.service;

import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepositoryImpl;
import com.flawden.Calculator.util.Tester;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    private EmployeerRepositoryImpl employeerRepository;

    public DepartmentService(EmployeerRepositoryImpl employeerRepository) {
        this.employeerRepository = employeerRepository;
    }

    public String salarySum(int department) throws IncorrectDepartmentNumberException {
        Tester.isValidDepartment(department);
        return "Итоговые затраты на зарплату в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).sum() + " рублей.";
    }

    public String minSalary(int department) throws IncorrectDepartmentNumberException {
        Tester.isValidDepartment(department);
        return "Минимальная зарплата в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).min().getAsInt() + " рублей.";
    }

    public String maxSalary(int department) throws IncorrectDepartmentNumberException {
        Tester.isValidDepartment(department);
        return "Максимальная зарплата в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).max().getAsInt() + " рублей.";
    }

    public String averageSalary(int department) throws IncorrectDepartmentNumberException {
        Tester.isValidDepartment(department);
        return "Средняя зарплата сотрудников в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).average().getAsDouble() + " рублей.";
    }

    public void salaryIncreaseInPercent(int percent, int department) throws IncorrectDepartmentNumberException {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        for (Employee employee : employeesInDepartment) {
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
        }
    }

    public Employee[] findEmployeesByDepartment(int department) throws IncorrectDepartmentNumberException {
        if (!Tester.isValidDepartment(department)) {
            return new Employee[0];
        }
        List<Employee> employeesByDepartment = new ArrayList<>();
        for (Employee employee : employeerRepository.getEmployees()) {
            if (employee.getDepartment() == department) {
                employeesByDepartment.add(employee);
            }
        }
        return employeesByDepartment.toArray(new Employee[0]);
    }

    public String employeesPrinter(int department) throws IncorrectDepartmentNumberException {
        Tester.isValidDepartment(department);
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
