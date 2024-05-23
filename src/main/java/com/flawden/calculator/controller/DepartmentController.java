package com.flawden.calculator.controller;

import com.flawden.calculator.exceptions.IncorrectDepartmentNumberException;
import com.flawden.calculator.model.Employee;
import com.flawden.calculator.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{department}/employees/print")
    public String employeesPrinter(@PathVariable int department) {
        return departmentService.employeesPrinter(department);
    }

    @GetMapping("/{department}/salary/sum")
    public String salarySum(@PathVariable int department) {
        return "Итоговые затраты на зарплату в отделе номер " + department + ": " + departmentService.salarySum(department) + " рублей.";
    }

    @GetMapping("/{department}/salary//min")
    public String minSalary(@PathVariable int department) {
        return "Минимальная зарплата в отделе номер " + department + ": " + departmentService.minSalary(department) + " рублей.";
    }

    @GetMapping("/{department}/salary/max")
    public String maxSalary(@PathVariable int department) {
        return "Максимальная зарплата в отделе номер " + department + ": " + departmentService.maxSalary(department) + " рублей.";
    }

    @GetMapping("/{department}/salary/average")
    public String averageSalary(@PathVariable int department) {
        return "Средняя зарплата сотрудников в отделе номер " + department + ": " + departmentService.averageSalary(department) + " рублей.";
    }

    @PostMapping("/{department}/salary/{percent}/increase-in-percent")
    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent, @PathVariable int department) {
        departmentService.salaryIncreaseInPercent(percent, department);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{department}/employees")
    public Employee[] findEmployeesByDepartment(@PathVariable int department) {
        return departmentService.findEmployeesByDepartment(department);
    }

}
