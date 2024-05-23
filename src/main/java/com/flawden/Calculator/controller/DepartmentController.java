package com.flawden.Calculator.controller;

import com.flawden.Calculator.exceptions.ArrayIsFullException;
import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.Calculator.exceptions.IncorrectEmployeeException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{department}/employees/print")
    public String employeesPrinter(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return departmentService.employeesPrinter(department);
    }

    @GetMapping("/{department}/salary/sum")
    public String salarySum(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return "Итоговые затраты на зарплату в отделе номер " + department + ": " + departmentService.salarySum(department) + " рублей.";
    }

    @GetMapping("/{department}/salary//min")
    public String minSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return "Минимальная зарплата в отделе номер " + department + ": " + departmentService.minSalary(department) + " рублей.";
    }

    @GetMapping("/{department}/salary/max")
    public String maxSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return "Максимальная зарплата в отделе номер " + department + ": " + departmentService.maxSalary(department) + " рублей.";
    }

    @GetMapping("/{department}/salary/average")
    public String averageSalary(@PathVariable int department) throws IncorrectDepartmentNumberException {
        return "Средняя зарплата сотрудников в отделе номер " + department + ": " + departmentService.averageSalary(department) + " рублей.";
    }

    @PostMapping("/{department}/salary/{percent}/increase-in-percent")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent, @PathVariable int department) throws IncorrectDepartmentNumberException {
        departmentService.salaryIncreaseInPercent(percent, department);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{department}/employees")
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
