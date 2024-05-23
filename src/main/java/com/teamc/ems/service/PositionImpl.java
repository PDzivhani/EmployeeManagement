package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.Position;
import com.teamc.ems.repository.PositionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PositionImpl implements PositionInit{
    @Autowired
    private PositionRepo positionRepo;

    @Override
    public List<Position> getAllPositions() {
        return positionRepo.findByDeletedFalse();
    }

    @Override
    public Position getPositionById(Long id) {
        return positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position with id " + id + " is not found"));
    }

    @Override
    public Position createPosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public void updatePosition(Long id, Position position) {
        Position positionFromDb = positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position with id " + id + " is not found"));
        position.setPositionId(id);
        positionFromDb.setPositionName(position.getPositionName());
        positionFromDb.setSalary(position.getSalary());
        positionRepo.save(positionFromDb);
    }

    @Override
    public void softDeletePosition(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found"));
        position.setDeleted(true);
        positionRepo.save(position);
    }

    @Override
    public void savePositions(Position position) {
        positionRepo.save(position);
    }
}
