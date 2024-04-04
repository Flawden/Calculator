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

    public String employeesPrinter(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        StringBuilder employeesInDepartmentAnswer = new StringBuilder();
        employeesInDepartmentAnswer.append("В отделе номер ").append(department).append(" были найдены следующие сотрудники:");
        Arrays.stream(employeesInDepartment).forEach(employee -> employeesInDepartmentAnswer
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
                .append(" рублей"));
        return employeesInDepartmentAnswer.toString();
    }

    public String salarySum() {
        double salarySum = 0;
        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }
        return "Итоговые затраты на зарплату: " + salarySum + " рублей.";
    }

    public String salarySum(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double salarySum = 0;
        for (Employee employee : employeesInDepartment) {
            salarySum += employee.getSalary();
        }
        return "Итоговые затраты на зарплату в отделе номер " + department + ": " + salarySum + " рублей.";
    }

    public String minSalary() {
        double minSalary = Double.MAX_VALUE;
        for (Employee employee : employees) {
            if (minSalary > employee.getSalary()) {
                minSalary = employee.getSalary();
            }
        }
        return "Минимальная зарплата: " + minSalary + " рублей.";
    }

    public String minSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double minSalary = Double.MAX_VALUE;
        for (Employee employee : employeesInDepartment) {
            if (minSalary > employee.getSalary()) {
                minSalary = employee.getSalary();
            }
        }
        return "Минимальная зарплата в отделе номер " + department + ": " + minSalary + " рублей.";
    }

    public String maxSalary() {
        double maxSalary = 0;
        for (Employee employee : employees) {
            if (maxSalary < employee.getSalary()) {
                maxSalary = employee.getSalary();
            }
        }
        return "Максимальная зарплата: " + maxSalary + " рублей.";
    }

    public String maxSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double maxSalary = 0;
        for (Employee employee : employeesInDepartment) {
            if (maxSalary < employee.getSalary()) {
                maxSalary = employee.getSalary();
            }
        }
        return "Максимальная зарплата в отделе номер " + department + ": " + maxSalary + " рублей.";
    }

    public String averageSalary() {
        double salarySum = 0;
        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }
        return "Средняя зарплата сотрудников: " + (salarySum / employees.size()) + " рублей.";
    }

    public String averageSalary(int department) throws IncorrectDepartmentNumber {
        Tester.isValidDepartment(department);
        Employee[] employeesInDepartment = findEmployeesByDepartment(department);
        double salarySum = 0;
        for (Employee employee : employeesInDepartment) {
            salarySum += employee.getSalary();
        }
        return "Средняя зарплата сотрудников в отделе номер " + department + ": " + (salarySum / employeesInDepartment.length) + " рублей.";
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
