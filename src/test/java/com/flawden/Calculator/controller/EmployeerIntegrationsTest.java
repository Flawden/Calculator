package com.flawden.Calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flawden.Calculator.exceptions.EmployeeNotFoundException;
import com.flawden.Calculator.exceptions.IncorrectEmployeeException;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepository;
import com.flawden.Calculator.service.EmployeeBookService;
import com.flawden.Calculator.util.Tester;
import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeerIntegrationsTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    private void setUp() {
        objectMapper = new ObjectMapper();
    }

    EmployeeBookController employeeBookController = new EmployeeBookController(new EmployeeBookService(new EmployeerRepository()));

    /*public List<Employee> getEmployee() {
        return employeeBookService.getEmployees();
    }*/

    @Test
    public void addEmployee() throws Exception {
        Employee valera = new Employee("Valera", "Iakob", "Levin", 1, 27000);
        mockMvc.perform(post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(valera)))
                        .andExpect(status().isOk());
    }

    @Test
    public void deleteEmployee() throws Exception {
        Employee valera = new Employee("Valera", "Iakob", "Levin", 1, 27000);
        mockMvc.perform(delete("/api/v1/employee/{id}", 1))
                .andExpect(status().isOk());
    }

//    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
//        return employeeBookService.getEmployeeById(id);
//    }
//
//    public String salarySum() {
//        return employeeBookService.salarySum();
//    }
//
//    public String minSalary() {
//        return employeeBookService.minSalary();
//    }
//
//    public String maxSalary() {
//        return employeeBookService.maxSalary();
//    }
//
//    public String averageSalary() {
//        return employeeBookService.averageSalary();
//    }
//
//    public ResponseEntity salaryIncreaseInPercent(@PathVariable int percent) {
//        employeeBookService.salaryIncreaseInPercent(percent);
//        return ResponseEntity.ok().build();
//    }

}
