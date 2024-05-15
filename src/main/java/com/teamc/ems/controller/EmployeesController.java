package com.teamc.ems.controller;

import com.teamc.ems.entity.Employees;
import com.teamc.ems.service.EmployeesInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/employees")
public class EmployeesController {

    @Autowired
    private EmployeesInit employeesInit;

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeesInit.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id) {
        Employees employee = employeesInit.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee) {
        return ResponseEntity.ok(employeesInit.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable Long id, @RequestBody Employees employee) {
        Employees updatedEmployee = employeesInit.updateEmployee(id, employee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeesInit.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
