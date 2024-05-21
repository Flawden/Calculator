package com.flawden.Calculator.repository;

import com.flawden.Calculator.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeerRepository {

    public EmployeerRepository(List<Employee> employees) {
        employees.add(new Employee("Firsname", "Patronymic", "Lastname", 1, 30000));
        employees.add(new Employee("Oleg", "Valerievich", "Sazanov", 2, 45000));
        employees.add(new Employee("Mavic", "Gavic", "Qavic", 1, 35000));
        employees.add(new Employee("One", "More", "Worker", 3, 15000));
        employees.add(new Employee("Another", "One", "Worker", 1, 30000));
        employees.add(new Employee("Something", "Else", "Sazanov", 2, 45000));
        employees.add(new Employee("Mavic", "Gavic", "Qavic", 1, 35000));
        employees.add(new Employee("Lad", "Pad", "Bad", 3, 15000));
        this.employees = employees;
    }

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void delete(int id) {
        employees.remove(id);
    }

}
