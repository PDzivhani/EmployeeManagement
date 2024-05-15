package com.teamc.ems.service;

import com.teamc.ems.entity.Employees;
import com.teamc.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeesImpl implements EmployeesInit{

    @Autowired
    private EmployeeRepo employeeRepo;

   @Override
    public List<Employees> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employees getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @Override
    public Employees createEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employees updateEmployee(Long id, Employees employee) {
        if (employeeRepo.existsById(id)) {
            employee.setId(id);
            return employeeRepo.save(employee);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}

