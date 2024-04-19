package com.flawden.Calculator.service;

import com.flawden.Calculator.exceptions.EmployeeNotFound;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumber;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.util.Tester;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeBookService {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployee(int id) {
        employees.remove(id);
    }

    public Employee getEmployeeById(int id) throws EmployeeNotFound {
        if(employees.size() < id) {
            throw new EmployeeNotFound("Ошибка! Сотрудник с указанным id не существует.");
        }
        return employees.get(id);
    }

    public String employeesPrinter(int department) throws IncorrectDepartmentNumber {
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

    public String salarySum() {
        return "Итоговые затраты на зарплату: " + employees.stream().mapToInt(employee -> (int) employee.getSalary()).sum() + " рублей.";
    }

    public String salarySum(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        return "Итоговые затраты на зарплату в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).sum() + " рублей.";
    }

    public String minSalary() {
        return "Минимальная зарплата: " + employees.stream().mapToInt(employee -> (int) employee.getSalary()).min().getAsInt() + " рублей.";
    }

    public String minSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        return "Минимальная зарплата в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).min().getAsInt() + " рублей.";
    }

    public String maxSalary() {
        return "Максимальная зарплата: " + employees.stream().mapToInt(employee -> (int) employee.getSalary()).max().getAsInt() + " рублей.";
    }

    public String maxSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        return "Максимальная зарплата в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).max().getAsInt() + " рублей.";
    }

    public String averageSalary() {
        return "Средняя зарплата сотрудников: " + employees.stream().mapToInt(employee -> (int) employee.getSalary()).average().getAsDouble() + " рублей.";
    }

    public String averageSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        return "Средняя зарплата сотрудников в отделе номер " + department + ": " + Arrays.stream(findEmployeesByDepartment(department)).toList().stream().mapToInt(employee -> (int) employee.getSalary()).average().getAsDouble() + " рублей.";
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
