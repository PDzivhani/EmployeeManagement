package com.teamc.ems.service;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.Position;
import com.teamc.ems.exceptionHandling.ResourceFoundException;
import com.teamc.ems.exceptionHandling.ResourceNotFoundException;
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
        Optional<Position> optional = positionRepo.findById(id);
        Position position;
        if(optional.isPresent()){
            position = optional.get();
        }
        else {
            throw new ResourceNotFoundException("Position with id" + id + "is not found");
        }
        return position;
    }

    @Override
    public Position createPosition(Position position) {
        try{
             return positionRepo.save(position);
            //throw  new ResourceFoundException("Position successfully created.");
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Unable to create position.");
        }

    }

    @Override
    public void savePosition(Position position) {
        try{
            this.positionRepo.save(position);
            throw new ResourceFoundException("Position saved");
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Cannot save position");
        }
    }

    @Override
    public void updatePosition(Long id, Position position) {
        Position positionDb = positionRepo.findById(id).get();
        if(positionRepo.existsById(id)){
            positionDb.setPositionId(id);
            positionDb.setPositionName(position.getPositionName());
            positionDb.setSalary(position.getSalary());

//            departmentDb.setEMPUsers(department.getEMPUsers());
            positionRepo.save(positionDb);
            throw new ResourceFoundException("Position with id " + id + " successfully updated");
        }
        else {
            // implement custom exception if position does not exist
            throw new ResourceNotFoundException("Cannot update. Position with id" + id + "does not exist");
        }
    }

    @Override
    public void softDeletePosition(Long id) {
        Optional <Position> PositionOptional = positionRepo.findById(id);
        if(PositionOptional.isPresent()) {
            Position position = PositionOptional.get();
            position.setDeleted(true);
            positionRepo.save(position);
            // custom success message implemented
            throw  new ResourceFoundException("Position with id " + id + " successfully deleted");
        } else {
            throw new ResourceNotFoundException("Position with id " + id + " does not exist");
        }
    }
}
