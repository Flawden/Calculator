package com.flawden.calculator.service;

import com.flawden.calculator.exceptions.EmployeeNotFoundException;
import com.flawden.calculator.model.Employee;
import com.flawden.calculator.repository.EmployeeRepository;
import com.flawden.calculator.repository.EmployeeRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeBookService {

    private EmployeeRepository employeeRepository;

    public EmployeeBookService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.delete(id);
    }

    public Employee getEmployeeById(int id) {
        if(employeeRepository.getEmployees().size() < id) {
            throw new EmployeeNotFoundException("Ошибка! Сотрудник с указанным id не существует.");
        }
        return employeeRepository.getEmployeeById(id);
    }

    public double salarySum() {
        return employeeRepository.getEmployees().stream().mapToDouble(Employee::getSalary).sum();
    }

    public double minSalary() {
        return employeeRepository.getEmployees().stream().mapToDouble(Employee::getSalary).min().getAsDouble();
    }

    public double maxSalary() {
        return employeeRepository.getEmployees().stream().mapToDouble(Employee::getSalary).max().getAsDouble();
    }

    public double averageSalary() {
        return employeeRepository.getEmployees().stream().mapToDouble(employee -> (double) employee.getSalary()).average().getAsDouble();
    }

    public void salaryIncreaseInPercent(int percent) {
        for (Employee employee : employeeRepository.getEmployees()) {
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
        }
    }
}
