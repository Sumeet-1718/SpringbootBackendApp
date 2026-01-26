package com.example.springboot_backend.service;

import com.example.springboot_backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService
{
    Employee saveEmployee(Employee employee);
    List<Employee> GetAllEmployees();
    Employee getEmployeeById(long id);
    Employee UpdateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
    Page<Employee> getAllEmployeesWithPagination(int page, int size);
    List<Employee> getEmployeesByFirstName(String firstName);




}
