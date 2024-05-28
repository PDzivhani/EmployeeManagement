package com.teamc.ems.repository;

import com.teamc.ems.entity.EMPUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<EMPUser, Long> {
    List<EMPUser>findByDeletedFalse();
    Optional<EMPUser> findByEmailAndPassword(String email, String password);

    List<EMPUser> findByDeletedTrue();
}
