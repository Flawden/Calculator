package com.flawden.calculator.model;

import java.util.Objects;

public class Employee {

    private static int employeeCounter = 0;
    private int id;
    private String firstname;
    private String patronymic;
    private String lastname;
    private int department;
    private double salary;

    public Employee(String firstname, String patronymic, String lastname, int department, double salary) {
        this.id = employeeCounter;
        employeeCounter++;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.department = department;
        this.salary = salary;
    }

    public static int getEmployeeCounter() {
        return employeeCounter;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && department == employee.department && Double.compare(salary, employee.salary) == 0 && Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname) && Objects.equals(patronymic, employee.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, patronymic, department, salary);
    }

    @Override
    public String toString() {
        return "Сотрудник:\n" +
                "id: " + id + "\n" +
                "Имя: " + firstname + "\n" +
                "Фамилия: " + lastname + "\n" +
                "Отчество: " + patronymic + "\n" +
                "Отдел: " + department + "\n" +
                "Зароботная плата: " + salary + " рублей\n";
    }

}
