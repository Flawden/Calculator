package com.flawden.Calculator.service;

import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepository;
import com.flawden.Calculator.util.Tester;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeBookService {

    private EmployeerRepository employeesRepository;

    public EmployeeBookService(EmployeerRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getEmployees() {
        return employeesRepository.getEmployees();
    }

    public void addEmployee(Employee employee) {
        employeesRepository.add(employee);
    }

    public void deleteEmployee(int id) {
        employeesRepository.delete(id);
    }

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        if(employeesRepository.getEmployees().size() < id) {
            throw new EmployeeNotFoundException("Ошибка! Сотрудник с указанным id не существует.");
        }
        return employeesRepository.getEmployeeById(id);
    }

    public String salarySum() {
        return "Итоговые затраты на зарплату: " + employeesRepository.getEmployees().stream().mapToInt(employee -> (int) employee.getSalary()).sum() + " рублей.";
    }

    public String minSalary() {
        return "Минимальная зарплата: " + employeesRepository.getEmployees().stream().mapToInt(employee -> (int) employee.getSalary()).min().getAsInt() + " рублей.";
    }

    public String maxSalary() {
        return "Максимальная зарплата: " + employeesRepository.getEmployees().stream().mapToInt(employee -> (int) employee.getSalary()).max().getAsInt() + " рублей.";
    }

    public String averageSalary() {
        return "Средняя зарплата сотрудников: " + employeesRepository.getEmployees().stream().mapToInt(employee -> (int) employee.getSalary()).average().getAsDouble() + " рублей.";
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
        for (Employee employee : employeesRepository.getEmployees()) {
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " до индексации.");
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
            System.out.println("Зарплата " + employee.getFirstname() + " " + employee.getPatronymic() + " " + employee.getLastname() + " равна " + employee.getSalary() + " после индексации.");
        }
    }
}
