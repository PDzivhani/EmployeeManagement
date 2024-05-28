package com.teamc.ems.controller;

import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.service.EmployeesInit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmployeesController {

    private static final Logger logger = Logger.getLogger(EmployeesController.class.getName());

    @Autowired
    private EmployeesInit employeeService;

    @GetMapping
    public ResponseEntity<List<EMPUser>> getAllEmployees() {
        logger.info("GET /api/employees called");
        try {
            List<EMPUser> employees = employeeService.getAllEmployees();
            logger.info("GET /api/employees successful");
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("GET /api/employees failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EMPUser> getEmployeeById(@PathVariable Long id) {
        logger.info("GET /api/employees/" + id + " called");
        try {
            EMPUser employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.severe("GET /api/employees/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("GET /api/employees/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/deleted")
    public ResponseEntity<List<EMPUser>> getDeletedEmployees() {
        logger.info("GET /api/employees/deleted called");
        try {
            List<EMPUser> employees = employeeService.getDeletedEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("GET /api/employees/deleted failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/recover/{id}")
    public ResponseEntity<Void> recoverEmployee(@PathVariable Long id) {
        logger.info("PUT /api/employees/recover/" + id + " called");
        try {
            employeeService.recoverEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.severe("PUT /api/employees/recover/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("PUT /api/employees/recover/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EMPUser> createEmployee(@RequestBody EMPUser employee) {
        logger.info("POST /api/employees called");
        try {
            EMPUser newEmployee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.severe("POST /api/employees failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


@PutMapping("/{id}")
public ResponseEntity<EMPUser> updateEmployee(@PathVariable Long id, @RequestBody EMPUser updatedEmployee) {
    try {
        EMPUser existingEmployee = employeeService.getEmployeeById(id);
        // Update fields
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setGender(updatedEmployee.getGender());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setAddress(updatedEmployee.getAddress());
        existingEmployee.setIdNumber(updatedEmployee.getIdNumber());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setStartDate(updatedEmployee.getStartDate());
        existingEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setEmergencyContactName(updatedEmployee.getEmergencyContactName());
        existingEmployee.setEmergencyContactRelationship(updatedEmployee.getEmergencyContactRelationship());
        existingEmployee.setEmergencyContactNo(updatedEmployee.getEmergencyContactNo());
        employeeService.saveEmployee(existingEmployee);
        return ResponseEntity.ok(existingEmployee);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("DELETE /api/employees/" + id + " called");
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.severe("DELETE /api/employees/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("DELETE /api/employees/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
