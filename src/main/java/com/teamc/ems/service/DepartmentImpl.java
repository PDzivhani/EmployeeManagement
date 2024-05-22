package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.repository.DepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentImpl implements DepartmentInit{
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findByDeletedFalse();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " is not found"));
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public void saveDepartments(Department department) {
        departmentRepo.save(department);
    }

    @Override
    public void updateDepartment(Long id, Department department) {
        Department departmentDb = departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with id " + id + " is not found"));
        department.setDepartmentId(id);
        departmentDb.setDepartmentName(department.getDepartmentName());
        departmentRepo.save(departmentDb);
    }

    @Override
    public void softDeleteDepartment(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        department.setDeleted(true);
        departmentRepo.save(department);
    }

}
