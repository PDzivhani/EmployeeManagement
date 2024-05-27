package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.exceptionHandling.ResourceFoundException;
import com.teamc.ems.exceptionHandling.ResourceNotFoundException;
import com.teamc.ems.repository.DepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentImpl implements DepartmentInit{
  @Autowired
  private DepartmentRepo departmentRepo;
  @Override
  public List<Department> getAllDepartments() {

      return departmentRepo.findByDeletedFalse();
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> optional = departmentRepo.findById(id);
        Department department;
        if(optional.isPresent()){
            department = optional.get();
        }
        else {
            throw new ResourceNotFoundException("Department with id" + id + "is not found");
        }
        return department;
    }

    @Override
    public Department createDepartment(Department department) {
        try{
            return departmentRepo.save(department);
            //throw  new ResourceFoundException("Department successfully created.");
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Unable to create department.");
        }
    }

    @Override
    public void saveDepartments(Department department) {
        //use data from another class when you don't have anything to return
        try{
            this.departmentRepo.save(department);
            throw new ResourceFoundException("Department saved");
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Cannot save department");
        }
    }

    @Override
    public void updateDepartment(Long id, Department department) {
        Department departmentDb = departmentRepo.findById(id).get();
        if(departmentRepo.existsById(id)){
            department.setDepartmentId(id);
            departmentDb.setDepartmentName(department.getDepartmentName());
//            departmentDb.setEMPUsers(department.getEMPUsers());
            departmentRepo.save(departmentDb);
            throw new ResourceFoundException("Department with id " + id + " successfully updated");
        }
        else {
            // implement custom exception if position does not exist
            throw new ResourceNotFoundException("Cannot update. Department with id" + id + "does not exist");
        }
    }

    @Override
    public void softDeleteDepartment(Long id) {
        Optional <Department> DeptOptional = departmentRepo.findById(id);
        if(DeptOptional.isPresent()) {
            Department department = DeptOptional.get();
            department.setDeleted(true);
            departmentRepo.save(department);
            // custom success message implemented
            throw  new ResourceFoundException("Department with id " + id + " successfully deleted");
        } else {
            throw new ResourceNotFoundException("Department with id " + id + " does not exist");
        }
    }

}
