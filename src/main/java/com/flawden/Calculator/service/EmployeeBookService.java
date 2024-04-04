package com.flawden.Calculator.service;

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

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    public void employeesPrinter(int department) throws IncorrectDepartmentNumber {
        if (!Tester.isValidDepartment(department)) {
            return;
        }
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        System.out.println("В отделе номер " + department + " были найдены следующие сотрудники:");
        Arrays.stream(employeesInDepartment).forEach(employee -> System.out.println("Сотрудник:\n" +
                "id: " + employee.getId() + "\n" +
                "Имя: " + employee.getFirstname() + "\n" +
                "Фамилия: " + employee.getLastname() + "\n" +
                "Отчество: " + employee.getPatronymic() + "\n" +
                "Зароботная плата: " + employee.getSalary() + " рублей"));
    }

    public void salarySum() {
        double salarySum = 0;
        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }
        System.out.println("Итоговые затраты на зарплату: " + salarySum + " рублей.");
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
        System.out.println("Средняя зарплата сотрудников: " + (salarySum / employees.size()) + " рублей.");
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

//    public void fullnamePrinter() {
//        Arrays.stream(employees.toArray()). //forEach(employee -> System.out.println(employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname()));
//    }

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
