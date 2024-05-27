package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.repository.DepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DepartmentImpl implements DepartmentInit {

    private static final Logger logger = Logger.getLogger(DepartmentImpl.class.getName());

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return departmentRepo.findByDeletedFalse();
    }

    @Override
    public Department getDepartmentById(Long id) {
        logger.info("Fetching department with id " + id);
        return departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id " + id + " is not found"));
    }

    @Override
    public Department createDepartment(Department department) {
        logger.info("Creating new department");
        return departmentRepo.save(department);
    }

    @Override
    public void updateDepartment(Long id, Department department) {
        logger.info("Updating department with id " + id);
        Department departmentDb = departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id " + id + " is not found"));
        departmentDb.setDepartmentName(department.getDepartmentName());
        departmentRepo.save(departmentDb);
    }

    @Override
    public void softDeleteDepartment(Long id) {
        logger.info("Soft deleting department with id " + id);
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setDeleted(true);
        departmentRepo.save(department);
    }

    @Override
    public void saveDepartments(Department department) {
        departmentRepo.save(department);
    }
}
