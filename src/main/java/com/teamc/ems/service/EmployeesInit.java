package com.teamc.ems.service;

import com.teamc.ems.entity.Employees;

import java.util.List;


public interface EmployeesInit {
    List<Employees> getAllEmployees();

    Employees getEmployeeById(Long id);

    Employees createEmployee(Employees employee);

    void saveEmployees(Employees employees);

    void updateEmployee(Long id, Employees employee);

    void softDeleteEmployee(Long id);
}
