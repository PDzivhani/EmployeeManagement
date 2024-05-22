package com.teamc.ems.repository;

import com.teamc.ems.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepo extends JpaRepository<Position, Long> {
    List<Position> findByDeletedFalse();

}
