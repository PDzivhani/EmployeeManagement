package com.teamc.ems.service;

import com.teamc.ems.entity.Position;
import com.teamc.ems.repository.PositionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PositionImpl implements PositionInit {
    private static final Logger logger = Logger.getLogger(PositionImpl.class.getName());

    @Autowired
    private PositionRepo positionRepo;

    @Override
    public List<Position> getAllPositions() {
        logger.info("Fetching all positions");
        return positionRepo.findByDeletedFalse();
    }

    @Override
    public Position getPositionById(Long id) {
        logger.info("Fetching position with id " + id);
        return positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position with id " + id + " is not found"));
    }

    @Override
    public Position createPosition(Position position) {
        logger.info("Creating new position");
        return positionRepo.save(position);
    }

    @Override
    public void updatePosition(Long id, Position position) {
        logger.info("Updating department with id " + id);
        Position positionFromDb = positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position with id " + id + " is not found"));
        position.setPositionId(id);
        positionFromDb.setPositionName(position.getPositionName());
        positionFromDb.setSalary(position.getSalary());
        positionRepo.save(positionFromDb);
    }

    @Override
    public void softDeletePosition(Long id) {
        logger.info("Soft deleting position with id " + id);

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
