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
        EMPUser EMPUser;
        if(optional.isPresent()){
            EMPUser = optional.get();
        }
        else {
            throw new RuntimeException("Employee with id" + id + "is not found");

        }
        return EMPUser;
    }

    @Override
    public EMPUser createEmployee(EMPUser employee) {
        return employeeRepo.save(employee);
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
           employeeRepo.save(EMPUserFromDb);
       }
    }

    @Override
    public void saveEmployees(EMPUser EMPUser) {
        //use data from another class when you don't have anything to return
       this.employeeRepo.save(EMPUser);
    }


    public void setProfilePicture(byte[] bytes) {
    }

    public void saveEmployee(EmployeesImpl employee) {
    }
}

