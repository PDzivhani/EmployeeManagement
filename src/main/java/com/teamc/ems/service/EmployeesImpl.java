package com.teamc.ems.service;

import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.repository.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeesImpl implements EmployeesInit{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<EMPUser> getAllEmployees() {
        return employeeRepo.findByDeletedFalse();
    }

    @Override
    public EMPUser getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " is not found"));
    }

    @Override
    public EMPUser createEmployee(EMPUser employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void updateEmployee(Long id, EMPUser employee) {
        EMPUser employeeFromDb = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " is not found"));
        employee.setId(id);
        employeeFromDb.setFirstName(employee.getFirstName());
        employeeFromDb.setLastName(employee.getLastName());
        employeeFromDb.setDateOfBirth(employee.getDateOfBirth());
        employeeFromDb.setEmail(employee.getEmail());
        employeeFromDb.setGender(employee.getGender());
        employeeFromDb.setAddress(employee.getAddress());
        employeeFromDb.setIdNumber(employee.getIdNumber());
        employeeFromDb.setStartDate(employee.getStartDate());
        employeeFromDb.setPhoneNumber(employee.getPhoneNumber());
        employeeFromDb.setPassword(employee.getPassword());
        employeeFromDb.setImage(employee.getImage());
        employeeFromDb.setEmergencyContactName(employee.getEmergencyContactName());
        employeeFromDb.setEmergencyContactRelationship(employee.getEmergencyContactRelationship());
        employeeFromDb.setEmergencyContactNo(employee.getEmergencyContactNo());
//        employeeFromDb.setRole(employee.getRole());
        employeeRepo.save(employeeFromDb);
    }
    @Override
    public void softDeleteEmployee(Long id) {
        EMPUser employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setDeleted(true);
        employeeRepo.save(employee);
    }

    @Override
    public void saveEmployees(EMPUser EMPUser) {
        employeeRepo.save(EMPUser);
    }

}

