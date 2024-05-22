package com.teamc.ems.service;

import com.teamc.ems.entity.Department;

import java.util.List;

public interface DepartmentInit {
    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department createDepartment(Department department);

    void saveDepartments(Department department);

    void updateDepartment(Long id, Department department);

    void softDeleteDepartment(Long id);
}
