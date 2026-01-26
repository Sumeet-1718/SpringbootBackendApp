package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.Employee;
import com.example.springboot_backend.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    @RequestMapping("/employees")
    public class EmployeeViewController {

        private final EmployeeService employeeService;

        public EmployeeViewController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping("/list")
        public String viewEmployees(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {

            Page<Employee> employeePage = employeeService.getAllEmployeesWithPagination(page, size);

            model.addAttribute("employees", employeePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", employeePage.getTotalPages());

            return "employees/employees"; // template
        }


    }
