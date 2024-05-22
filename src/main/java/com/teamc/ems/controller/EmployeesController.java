package com.teamc.ems.controller;

import com.teamc.ems.entity.Employees;
import com.teamc.ems.service.EmployeesImpl;
import com.teamc.ems.service.EmployeesInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    @Autowired
    private EmployeesImpl employees;

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employees.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id) {
        Employees employee = employees.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee) {
        return ResponseEntity.ok(employees.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employees employee) {
        this.employees.updateEmployee(id, employee);
//        Employees updatedEmployee = employees.updateEmployee(id, employee);
//        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employees.softDeleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
