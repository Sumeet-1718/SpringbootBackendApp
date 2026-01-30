package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository  extends JpaRepository <Employee, Long>{

    @Query(name = "Employee.findByFirstName")
    List<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query(name= "Employee.findById")
    Optional<Employee> findById(@Param("id") long id);

}
