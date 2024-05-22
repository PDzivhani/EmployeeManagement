package com.teamc.ems.service;

import com.teamc.ems.entity.User;
import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.Position;
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
    public List<User> getAllEmployees() {
       return employeeRepo.findByDeletedFalse();
    }

    public void softDeleteEmployee(Long id) {
       Optional<User> userOptional = employeeRepo.findById(id);
       if(userOptional.isPresent()) {
           User user = userOptional.get();
           user.setDeleted(true);
           employeeRepo.save(user);
       } else {
           throw new EntityNotFoundException("not found");
       }
    }

    @Override
    public User getEmployeeById(Long id) {
        Optional<User> optional = employeeRepo.findById(id);
        User user;
        if(optional.isPresent()){
            user = optional.get();
        }
        else {
            throw new RuntimeException("Employee with id" + id + "is not found");

        }
        return user;
    }

    @Override
    public User createEmployee(User employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void updateEmployee(Long id, User employee) {
       User userFromDb = employeeRepo.findById(id).get();
       if(employeeRepo.existsById(id)){
           employee.setId(id);
           userFromDb.setFirstName(employee.getFirstName());
           userFromDb.setDateOfBirth(employee.getDateOfBirth());
           userFromDb.setEmail(employee.getEmail());
           userFromDb.setGender(employee.getGender());
           userFromDb.setAddress(employee.getAddress());
           userFromDb.setIdNumber(employee.getIdNumber());
           userFromDb.setLastName(employee.getLastName());
           userFromDb.setStartDate(employee.getStartDate());
           userFromDb.setPhoneNumber(employee.getPhoneNumber());
           userFromDb.setPassword(employee.getPassword());
           userFromDb.setImage(employee.getImage());
           userFromDb.setEmergencyContactRelationship(employee.getEmergencyContactRelationship());
           userFromDb.setEmergencyContactNo(employee.getEmergencyContactNo());
           userFromDb.setEmergencyContactName(employee.getEmergencyContactName());
           employeeRepo.save(userFromDb);
       }
    }

    @Override
    public void saveEmployees(User user) {
        //use data from another class when you don't have anything to return
       this.employeeRepo.save(user);
    }

}

