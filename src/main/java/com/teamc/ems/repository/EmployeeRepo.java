package com.teamc.ems.repository;

import com.teamc.ems.entity.EMPUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<EMPUser, Long> {
    List<EMPUser>findByDeletedFalse();

    List<EMPUser> findByDeletedTrue();
}
