package com.flawden.calculator.repository;

import com.flawden.calculator.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    void add(Employee employee);

    void delete(int id);

}
