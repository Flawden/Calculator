package com.flawden.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flawden.calculator.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationsTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

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
    public void salaryIncreaseInPercent() throws Exception {
        mockMvc.perform(post("/api/v1/employee/increase-in-percent/{percent}", 20))
                .andExpect(status().isOk());
    }
}
