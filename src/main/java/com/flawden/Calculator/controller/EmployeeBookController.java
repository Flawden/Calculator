package com.flawden.Calculator.controller;

import com.flawden.Calculator.exceptions.ArrayIsFull;
import com.flawden.Calculator.exceptions.EmployeeNotFound;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumber;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.service.EmployeeBookService;
import com.flawden.Calculator.util.Tester;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeBookController {

    private EmployeeBookService employeeBookService;

    public EmployeeBookController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping
    public List<Employee> addEmployee() {
        return employeeBookService.getEmployees();
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        employeeBookService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void deleteEmployee(int id) {
        employeeBookService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFound {
        return employeeBookService.getEmployeeById(id);
    }

    @GetMapping("/sum")
    public String salarySum() {
        return employeeBookService.salarySum();
    }

    @GetMapping("/sum/{department}")
    public String salarySum(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.salarySum(department);
    }

    @GetMapping("/print/{department}")
    public String employeesPrinter(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.employeesPrinter(department);
    }

    @GetMapping("/min-salary")
    public String minSalary() {
        return employeeBookService.minSalary();
    }

    @GetMapping("/min-salary/{department}")
    public String minSalary(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.minSalary(department);
    }

    @GetMapping("/max-salary")
    public String maxSalary() {
        return employeeBookService.maxSalary();
    }

    @GetMapping("max-salary/{department}")
    public String maxSalary(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.maxSalary(department);
    }

    @GetMapping("/average-salary")
    public String averageSalary() {
        return employeeBookService.averageSalary();
    }

    @GetMapping("/average-salary/{department}")
    public String averageSalary(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.averageSalary(department);
    }

    @PostMapping("/increase-in-percent/{percent}")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent) {
        employeeBookService.salaryIncreaseInPercent(percent);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/increase-in-percent/{percent}/{department}")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent, @PathVariable int department) throws IncorrectDepartmentNumber {
        employeeBookService.salaryIncreaseInPercent(percent, department);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-department/{department}")
    public Employee[] findEmployeesByDepartment(@PathVariable int department) throws IncorrectDepartmentNumber {
        return employeeBookService.findEmployeesByDepartment(department);
    }

    @ExceptionHandler(IncorrectDepartmentNumber.class)
    private String handler(IncorrectDepartmentNumber e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeNotFound.class)
    private String anotherHandler(EmployeeNotFound e) {
        return e.getMessage();
    }

    @ExceptionHandler(ArrayIsFull.class)
    private String oneMoreHandler(ArrayIsFull e) {
        return e.getMessage();
    }
}
