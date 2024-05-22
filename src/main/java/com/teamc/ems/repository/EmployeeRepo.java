package com.teamc.ems.repository;

import com.teamc.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<User, Long> {
    List<User>findByDeletedFalse();

}
