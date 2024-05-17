package com.teamc.ems.repository;

import com.teamc.ems.entity.Employees;
import com.teamc.ems.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
}
