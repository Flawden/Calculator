package com.flawden.Calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flawden.Calculator.model.Employee;
import com.flawden.Calculator.repository.EmployeerRepository;
import com.flawden.Calculator.service.EmployeeBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeerIntegrationsTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
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

    @Test
    public void getEmployeeById() throws Exception {
        mockMvc.perform(get("/api/v1/employee/{id}", 1))
                .andExpect(content().string(containsString("\"Oleg\",\"patronymic\":\"Valerievich\",\"lastname\":\"Sazanov\"")));
    }

    @Test
    public void salarySum() throws Exception {
        mockMvc.perform(get("/api/v1/employee/sum"))
                .andExpect(content().string("Итоговые затраты на зарплату: 250000 рублей."));
    }

    @Test
    public void minSalary() throws Exception {
        mockMvc.perform(get("/api/v1/employee/min-salary"))
                .andExpect(content().string("Минимальная зарплата: 15000 рублей."));
    }

    @Test
    public void maxSalary() throws Exception {
        mockMvc.perform(get("/api/v1/employee/max-salary"))
                .andExpect(content().string("Максимальная зарплата: 45000 рублей."));
    }

    @Test
    public void averageSalary() throws Exception {
        mockMvc.perform(get("/api/v1/employee/average-salary"))
                .andExpect(content().string("Средняя зарплата сотрудников: 31250.0 рублей."));
    }

    @Test
    public void salaryIncreaseInPercent() throws Exception {
        mockMvc.perform(post("/api/v1/employee/increase-in-percent/{percent}", 20))
                .andExpect(status().isOk());
    }
}
