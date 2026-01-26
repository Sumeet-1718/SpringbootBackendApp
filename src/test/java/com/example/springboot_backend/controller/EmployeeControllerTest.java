package com.example.springboot_backend.controller;
import com.example.springboot_backend.model.Employee;
import com.example.springboot_backend.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

    class EmployeeControllerTest {

        @Mock
        private EmployeeService employeeService;

        @InjectMocks
        private EmployeeController employeeController;

        private Employee employee1;
        private Employee employee2;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            employee1 = new Employee();
            employee1.setId(1L);
            employee1.setFirstName("John");
            employee1.setLastName("Doe");
            employee1.setEmail("john.doe@example.com");

            employee2 = new Employee();
            employee2.setId(2L);
            employee2.setFirstName("Jane");
            employee2.setLastName("Smith");
            employee2.setEmail("jane.smith@example.com");
        }


        @Test
        void testSaveEmployee() {
            when(employeeService.saveEmployee(employee1)).thenReturn(employee1);

            ResponseEntity<Employee> response = employeeController.saveEmployee(employee1);

            // Print the HTTP status and body
            System.out.println("HTTP Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(employee1, response.getBody());


        }
    }
