package com.teamc.ems.controller;

import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.service.EmployeesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EMPUser> createEmployee(@RequestBody EMPUser employee) {
        return ResponseEntity.ok(employees.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EMPUser employee) {
        this.employees.updateEmployee(id, employee);
//        Employees updatedEmployee = employees.updateEmployee(id, employee);
//        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employees.softDeleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {

        EMPUser employee = employees.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "profile";
    }

    @PostMapping("/uploadProfilePicture/{id}")
    public String uploadProfilePicture(@PathVariable Long id, @RequestParam ("image")MultipartFile image, Model model) throws IOException {
        EMPUser employee = employees.getEmployeeById(id);
        employee.setProfilePicture(image.getBytes());
        employees.saveEmployee(employees);
        model.addAttribute("employee", employee);
        return "Profile";
    }
}
