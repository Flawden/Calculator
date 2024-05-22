package com.flawden.Calculator.service;

import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeBookService {

    private EmployeerRepositoryImpl employeesRepository;

    public EmployeeBookService(EmployeerRepositoryImpl employeesRepository) {
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

    public long salarySum() {
        return employeesRepository.getEmployees().stream().mapToLong(employee -> (long) employee.getSalary()).sum();
    }

    public long minSalary() {
        return employeesRepository.getEmployees().stream().mapToLong(employee -> (long) employee.getSalary()).min().getAsLong();
    }

    public long maxSalary() {
        return employeesRepository.getEmployees().stream().mapToLong(employee -> (long) employee.getSalary()).max().getAsLong();
    }

    public double averageSalary() {
        return employeesRepository.getEmployees().stream().mapToDouble(employee -> (double) employee.getSalary()).average().getAsDouble();
    }

    public void salaryIncreaseInPercent(int percent) {
        for (Employee employee : employeesRepository.getEmployees()) {
            employee.setSalary(employee.getSalary() / 100 * (100 + percent));
        }
    }
}
