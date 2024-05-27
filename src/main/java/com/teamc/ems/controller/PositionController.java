package com.teamc.ems.controller;

import com.teamc.ems.entity.Department;
import com.teamc.ems.entity.Position;
import com.teamc.ems.service.PositionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    private PositionImpl positions;

    @GetMapping
    public List<Position> getAllPositions() {
        return positions.getAllPositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Position position = positions.getPositionById(id);
        return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        return ResponseEntity.ok(positions.createPosition(position));
    }

    @PostMapping
    public void savePosition(@RequestBody Position position){
        this.positions.savePosition(position);
    }

    @PutMapping("/{id}")
    public void updatePosition(@PathVariable Long id, @RequestBody Position position) {
        this.positions.updatePosition(id, position);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positions.softDeletePosition(id);
        return ResponseEntity.ok().build();
    }
}
