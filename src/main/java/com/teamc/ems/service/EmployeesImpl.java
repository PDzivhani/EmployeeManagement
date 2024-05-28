package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.exceptionHandling.CustomErrorResponse;
import com.teamc.ems.exceptionHandling.CustomSuccessResponse;
import com.teamc.ems.exceptionHandling.ResourceFoundException;
import com.teamc.ems.exceptionHandling.ResourceNotFoundException;
import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.entity.Position;
import com.teamc.ems.repository.DepartmentRepo;
import com.teamc.ems.repository.EmployeeRepo;
import com.teamc.ems.repository.PositionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeesImpl implements EmployeesInit {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private PositionRepo positionRepo;

    @Override
    public List<EMPUser> getAllEmployees() {
        return employeeRepo.findByDeletedFalse();
    }
    @Override
    public List<EMPUser> getDeletedEmployees() {
        return employeeRepo.findByDeletedTrue();
    }
    @Override
    public EMPUser getEmployeeById(Long id) {
        Optional<EMPUser> optional = employeeRepo.findById(id);
        EMPUser empUser;
        if(optional.isPresent()){
            empUser = optional.get();
        }
        else {
            // implement our custom exception on getting an employee by id
            throw new ResourceNotFoundException("Employee with id " + id + " is not found.");
        }
        return empUser;
    }

    @Override
    public EMPUser createEmployee(EMPUser employee) {
       try {
           return employeeRepo.save(employee);
           //throw  new ResourceFoundException("Employee successfully created.");
       } catch (Exception e){
           throw new ResourceNotFoundException("Unable to create employee.");
       }

    }
@Override
public void updateEmployee(Long id, EMPUser employee) {
    EMPUser employeeFromDb = employeeRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee with id " + id + " is not found"));

    Department department = departmentRepo.findById(employee.getDepartment().getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department with id " + employee.getDepartment().getDepartmentId() + " is not found"));

    Position position = positionRepo.findById(employee.getPosition().getPositionId())
            .orElseThrow(() -> new RuntimeException("Position with id " + employee.getPosition().getPositionId() + " is not found"));

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
    employeeFromDb.setRole(employee.getRole());
    employeeFromDb.setDepartment(department);
    employeeFromDb.setPosition(position);

    employeeRepo.save(employeeFromDb);
}

    @Override
    public void saveEmployee(EMPUser EMPUser) {
        try {
            this.employeeRepo.save(EMPUser);
            throw new ResourceFoundException("Employee saved");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot Save Employee");
        }
    }

    public void deleteEmployee(Long id) {
        EMPUser employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setDeleted(true);
        employeeRepo.save(employee);
    }

    @Override
    public void recoverEmployee(Long id) {
        EMPUser employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setDeleted(false);
        employeeRepo.save(employee);
    }
}

