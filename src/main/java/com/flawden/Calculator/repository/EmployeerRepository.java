package com.flawden.Calculator.repository;

import com.flawden.Calculator.model.Employee;

import java.util.List;

public interface EmployeerRepository {

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    void add(Employee employee);

    void delete(int id);

}
