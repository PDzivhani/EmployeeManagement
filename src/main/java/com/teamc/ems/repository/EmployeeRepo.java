package com.teamc.ems.repository;

import com.teamc.ems.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    List<Employees>findByDeletedFalse();

}
