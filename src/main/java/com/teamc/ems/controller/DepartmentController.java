package com.teamc.ems.controller;

import com.teamc.ems.entity.Department;
import com.teamc.ems.service.DepartmentInit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DepartmentController {

    private static final Logger logger = Logger.getLogger(DepartmentController.class.getName());

    @Autowired
    private DepartmentInit departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        logger.info("GET /api/departments called");
        try {
            List<Department> departments = departmentService.getAllDepartments();
            logger.info("GET /api/departments successful");
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("GET /api/departments failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        logger.info("GET /api/departments/" + id + " called");
        try {
            Department department = departmentService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.severe("GET /api/departments/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("GET /api/departments/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        logger.info("POST /api/departments called");
        try {
            Department newDepartment = departmentService.createDepartment(department);
            return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.severe("POST /api/departments failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        logger.info("PUT /api/departments/" + id + " called");
        try {
            departmentService.updateDepartment(id, department);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.severe("PUT /api/departments/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("PUT /api/departments/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.info("DELETE /api/departments/" + id + " called");
        try {
            departmentService.softDeleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.severe("DELETE /api/departments/" + id + " not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.severe("DELETE /api/departments/" + id + " failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
