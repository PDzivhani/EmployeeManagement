package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.Position;
import com.teamc.ems.repository.PositionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PositionImpl implements PositionInit{
    @Autowired
    private PositionRepo positionRepo;
    @Override
    public List<Position> getAllPositions() {
        return positionRepo.findByDeletedFalse();
    }

    @Override
    public Position getPositionById(Long id) {
        Optional<Position> optional = positionRepo.findById(id);
        Position position;
        if(optional.isPresent()){
            position = optional.get();
        }
        else {
            throw new RuntimeException("Position with id" + id + "is not found");
        }
        return position;
    }

    @Override
    public Position createPosition(Position position) {
        return positionRepo.save(position);
    }

    @Override
    public void savePositions(Position position) {
         this.positionRepo.save(position);
    }

    @Override
    public void updatePosition(Long id, Position position) {
        Position positionDb = positionRepo.findById(id).get();
        if(positionRepo.existsById(id)){
            position.setPositionId(id);
            positionDb.setPositionName(position.getPositionName());
//            departmentDb.setEMPUsers(department.getEMPUsers());
            positionRepo.save(positionDb);
        }
    }

    @Override
    public void softDeletePosition(Long id) {
        Optional <Position> PositionOptional = positionRepo.findById(id);
        if(PositionOptional.isPresent()) {
            Position position = PositionOptional.get();
            position.setDeleted(true);
            positionRepo.save(position);
        } else {
            throw new EntityNotFoundException("not found");
        }
    }
}
