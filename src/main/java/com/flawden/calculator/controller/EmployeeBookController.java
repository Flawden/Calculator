package com.flawden.calculator.controller;

import com.flawden.calculator.model.Employee;
import com.flawden.calculator.service.EmployeeBookService;
import com.flawden.calculator.util.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeBookController {

    private EmployeeBookService employeeBookService;

    public EmployeeBookController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping
    public List<Employee> getEmployee() {
        return employeeBookService.getEmployees();
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        Validator.isValidEmployee(employee);
        employeeBookService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        employeeBookService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeBookService.getEmployeeById(id);
    }

    @GetMapping("/sum")
    public String salarySum() {
        return "Итоговые затраты на зарплату: " + employeeBookService.salarySum() + " рублей.";
    }

    @GetMapping("/min-salary")
    public String minSalary() {
        return "Минимальная зарплата: " + employeeBookService.minSalary() + " рублей.";
    }

    @GetMapping("/max-salary")
    public String maxSalary() {
        return "Максимальная зарплата: " + employeeBookService.maxSalary() + " рублей.";
    }

    @GetMapping("/average-salary")
    public String averageSalary() {
        return "Средняя зарплата сотрудников: " + employeeBookService.averageSalary() + " рублей.";
    }

    @PostMapping("/increase-in-percent/{percent}")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent) {
        employeeBookService.salaryIncreaseInPercent(percent);
        return ResponseEntity.ok().build();
    }

}
