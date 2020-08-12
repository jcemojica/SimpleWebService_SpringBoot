package com.mojica.springboot.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeResource {
    @Autowired
    private EmployeeRepo employeeRepo;

    //CREATE
    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
        Employee emp = employeeRepo.save(employee);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(emp.getEmployeeId()).toUri();

        return ResponseEntity.created(loc).build();
    }

    //READ
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getOneEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(id);

        if(!employee.isPresent())
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found");

        return employee.get();
    }

    //UPDATE
    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable int id){
        Optional<Employee> emp = employeeRepo.findById(id);

        if(!emp.isPresent())
            return ResponseEntity.notFound().build();

        employee.setEmployeeId(id);
        employeeRepo.save(employee);

        return ResponseEntity.noContent().build();
    }

    //DELETE
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeRepo.deleteById(id);
    }

}
