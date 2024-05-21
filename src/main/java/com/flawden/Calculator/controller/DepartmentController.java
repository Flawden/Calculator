package com.flawden.Calculator.controller;

import com.flawden.Calculator.exceptions.ArrayIsFullException;
import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.exceptions.IncorrectEmployeeException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/print/{department}/employees")
    public String employeesPrinter(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.employeesPrinter(department);
    }

    @GetMapping("/sum/{department}/salary/sum")
    public String salarySum(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.salarySum(department);
    }

    @GetMapping("/min-salary/{department}/salary/min")
    public String minSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.minSalary(department);
    }

    @GetMapping("max-salary/{department}/salary/max")
    public String maxSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.maxSalary(department);
    }

    @GetMapping("/average-salary/{department}/salary/average")
    public String averageSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.averageSalary(department);
    }

    @PostMapping("/increase-in-percent/{percent}/{department}/salary")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent, @PathVariable int department) throws IncorrectDepartmentNumberException {
        departmentService.salaryIncreaseInPercent(percent, department);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-department/{department}")
    public Employee[] findEmployeesByDepartment(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.findEmployeesByDepartment(department);
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
