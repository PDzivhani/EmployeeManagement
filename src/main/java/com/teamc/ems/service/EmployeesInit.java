package com.teamc.ems.service;

import com.teamc.ems.entity.User;

import java.util.List;


public interface EmployeesInit {
    List<User> getAllEmployees();

    User getEmployeeById(Long id);

    User createEmployee(User employee);

    void saveEmployees(User user);

    void updateEmployee(Long id, User employee);

    void softDeleteEmployee(Long id);
}
