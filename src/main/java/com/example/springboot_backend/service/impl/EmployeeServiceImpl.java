package com.example.springboot_backend.service.impl;

import com.example.springboot_backend.client.AddressFeignClient;
import com.example.springboot_backend.exception.ResourceNotFoundException;
import com.example.springboot_backend.model.Address;
import com.example.springboot_backend.model.Employee;
import com.example.springboot_backend.repository.EmployeeRepository;
import com.example.springboot_backend.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressFeignClient addressFeignClient) {
        this.employeeRepository = employeeRepository;
        this.addressFeignClient=addressFeignClient;
    }

    private EmployeeRepository employeeRepository;
    private AddressFeignClient addressFeignClient;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> GetAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee= employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));

       Address address=addressFeignClient.getAddress(id);
       employee.setAddress(address);
       return employee;

    }



    @Override
    public Employee UpdateEmployee(Employee employee, long id) {
        Employee existingEmployee= employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id ));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);
        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id ));
        employeeRepository.deleteById(id);
    }
    @Override
    public Page<Employee> getAllEmployeesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }


}
