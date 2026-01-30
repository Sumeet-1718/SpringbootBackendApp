package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.Employee;
import com.example.springboot_backend.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
@PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
       return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
@GetMapping
public List<Employee> GetAllEmployees(){
        return employeeService.GetAllEmployees();
}
    @Operation(
            summary = "Get employee by ID",
            description = "Fetch employee details using employee ID"
    )
    @ApiResponse(responseCode = "200", description = "Employee found")


    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }
@PutMapping("{id}")
public ResponseEntity<Employee> UpdateEmployee(@PathVariable("id") long id ,@RequestBody Employee employee){
    return new ResponseEntity<Employee>(employeeService.UpdateEmployee(employee,id), HttpStatus.OK);
}
@DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);

}

    @GetMapping("/pagination")
    public ResponseEntity<Page<Employee>> getEmployeesWithPagination(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(
                employeeService.getAllEmployeesWithPagination(page, size)
        );
    }

    @GetMapping("/by-first-name")
    public List<Employee> getEmployeesByFirstName(@RequestParam String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

   @GetMapping("/by-id")
  public Optional<Employee> findById(@RequestParam long id){
        return employeeService.findById(id);
   }

}
