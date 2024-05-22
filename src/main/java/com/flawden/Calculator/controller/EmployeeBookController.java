package com.flawden.Calculator.controller;

import com.flawden.Calculator.exceptions.ArrayIsFullException;
import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.exceptions.IncorrectEmployeeException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.service.EmployeeBookService;
import com.flawden.Calculator.util.Tester;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity addEmployee(@RequestBody Employee employee) throws IncorrectEmployeeException {
        Tester.isValidEmployee(employee);
        employeeBookService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        employeeBookService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
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

    @ExceptionHandler(IncorrectDepartmentNumberException.class)
    private String handler(IncorrectDepartmentNumberException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    private String anotherHandler(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ArrayIsFullException.class)
    private String oneMoreHandler(ArrayIsFullException e) {
        return e.getMessage();
    }

    @ExceptionHandler(IncorrectEmployeeException.class)
    private ResponseEntity oneMoreHandler(IncorrectEmployeeException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
