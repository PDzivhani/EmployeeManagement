package com.teamc.ems.service;

import com.teamc.ems.exceptionHandling.ResourceNotFoundException;
import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.repository.EmployeeRepo;
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
public class EmployeesImpl implements EmployeesInit{

    @Autowired
    private EmployeeRepo employeeRepo;

   @Override
    public List<EMPUser> getAllEmployees() {
       return employeeRepo.findByDeletedFalse();
    }

    public void softDeleteEmployee(Long id) {
       Optional<EMPUser> userOptional = employeeRepo.findById(id);
       if(userOptional.isPresent()) {
           EMPUser EMPUser = userOptional.get();
           EMPUser.setDeleted(true);
           employeeRepo.save(EMPUser);
       } else {
           throw new EntityNotFoundException("not found");
       }
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
            throw new ResourceNotFoundException("Employee with id " + id + " is not found");
        }
        return empUser;
    }

    @Override
    public EMPUser createEmployee(EMPUser employee) {
       try {
           return employeeRepo.save(employee);
       } catch (Exception e){
           throw new ResourceNotFoundException("Unable to create employee.");
       }

    }

    @Override
    public void updateEmployee(Long id, EMPUser employee) {
       EMPUser EMPUserFromDb = employeeRepo.findById(id).get();
       if(employeeRepo.existsById(id)){
           employee.setId(id);
           EMPUserFromDb.setFirstName(employee.getFirstName());
           EMPUserFromDb.setDateOfBirth(employee.getDateOfBirth());
           EMPUserFromDb.setEmail(employee.getEmail());
           EMPUserFromDb.setGender(employee.getGender());
           EMPUserFromDb.setAddress(employee.getAddress());
           EMPUserFromDb.setIdNumber(employee.getIdNumber());
           EMPUserFromDb.setLastName(employee.getLastName());
           EMPUserFromDb.setStartDate(employee.getStartDate());
           EMPUserFromDb.setPhoneNumber(employee.getPhoneNumber());
           EMPUserFromDb.setPassword(employee.getPassword());
           EMPUserFromDb.setImage(employee.getImage());
           EMPUserFromDb.setEmergencyContactRelationship(employee.getEmergencyContactRelationship());
           EMPUserFromDb.setEmergencyContactNo(employee.getEmergencyContactNo());
           EMPUserFromDb.setEmergencyContactName(employee.getEmergencyContactName());
           EMPUserFromDb.setDepartment(employee.getDepartment());
           EMPUserFromDb.setPosition(employee.getPosition());
           employeeRepo.save(EMPUserFromDb);
       }
       else {
           // implement custom exception if employee does not exist
           throw new ResourceNotFoundException("Cannot update. Employee with id" + id + "does not exist");
       }
    }

    @Override
    public void saveEmployee(EMPUser EMPUser) {
       try{
           this.employeeRepo.save(EMPUser);
       }
       catch (Exception e){
           throw new ResourceNotFoundException("Cannot Save Employee");
       }


    }

}

