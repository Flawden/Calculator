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

    @DeleteMapping
    public void deleteEmployee(int id) {
        employeeBookService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        return employeeBookService.getEmployeeById(id);
    }

    @GetMapping("/sum")
    public String salarySum() {
        return employeeBookService.salarySum();
    }

    @GetMapping("/min-salary")
    public String minSalary() {
        return employeeBookService.minSalary();
    }

    @GetMapping("/max-salary")
    public String maxSalary() {
        return employeeBookService.maxSalary();
    }

    @GetMapping("/average-salary")
    public String averageSalary() {
        return employeeBookService.averageSalary();
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
