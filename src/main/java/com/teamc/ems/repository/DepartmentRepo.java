package com.teamc.ems.repository;

import com.teamc.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findByDeletedFalse();

}
