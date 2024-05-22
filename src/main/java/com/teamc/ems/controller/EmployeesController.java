package com.teamc.ems.controller;

import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.exceptionHandling.CustomException;
import com.teamc.ems.repository.EmployeeRepo;
import com.teamc.ems.service.EmployeesImpl;
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
    public List<EMPUser> getAllEmployees() {
        return employees.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EMPUser> getEmployeeById(@PathVariable Long id) {
        EMPUser employee = employees.getEmployeeById(id);
        if (id.equals(id)){
            throw new CustomException("USER_NOT_FOUND", "User with ID " + id + " not found");
        } else {

        }

        //return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EMPUser> createEmployee(@RequestBody EMPUser employee) {
        return ResponseEntity.ok(employees.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EMPUser employee) {
        this.employees.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employees.softDeleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
