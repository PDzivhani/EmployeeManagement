package com.teamc.ems.repository;

import com.teamc.ems.entity.EMPUser;
import com.teamc.ems.service.EmployeesImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EMPUser, Long> {
    static EmployeesImpl save(EmployeesImpl employee) {
        return employee;
    }

    List<EMPUser>findByDeletedFalse();

}
