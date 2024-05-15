package com.teamc.ems.service;

import com.teamc.ems.entity.Employees;
import com.teamc.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        Optional<Employees> optional = employeeRepo.findById(id);
        Employees employees;
        if(optional.isPresent()){
            employees = optional.get();
        }
        else {
            throw new RuntimeException("Employee with id" + id + "is not found");

        }
        return employees;
    }

    @Override
    public Employees createEmployee(Employees employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void updateEmployee(Long id, Employees employee) {
       Employees employeesFromDb = employeeRepo.findById(id).get();
       if(employeeRepo.existsById(id)){
           employee.setId(id);
           employeesFromDb.setName(employee.getName());
           employeesFromDb.setBirthday(employee.getBirthday());
           employeesFromDb.setEmail(employee.getEmail());
           employeesFromDb.setDepartment(employee.getDepartment());
           employeesFromDb.setGender(employee.getGender());
           employeesFromDb.setAddress(employee.getAddress());
           employeesFromDb.setIdNo(employee.getIdNo());
           employeesFromDb.setWorkRole(employee.getWorkRole());
           employeesFromDb.setSurname(employee.getSurname());
           employeesFromDb.setStartDate(employee.getStartDate());
           employeesFromDb.setPhoneNumber(employee.getPhoneNumber());
           employeesFromDb.setPassword(employee.getPassword());
           employeesFromDb.setImage(employee.getImage());
           employeesFromDb.setEmergencyContactRelationship(employee.getEmergencyContactRelationship());
           employeesFromDb.setEmergencyContactNo(employee.getEmergencyContactNo());
           employeesFromDb.setEmergencyContactName(employee.getEmergencyContactName());
           employeeRepo.save(employeesFromDb);
       }
    }

    @Override
    public void saveEmployees(Employees employees) {
        //use data from another class when you don't have anything to return
       this.employeeRepo.save(employees);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}

