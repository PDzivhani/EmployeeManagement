package com.teamc.ems.controller;

import com.teamc.ems.entity.Position;
import com.teamc.ems.service.PositionInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/positions")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PositionController {
    private static final Logger logger = Logger.getLogger(PositionController.class.getName());

    @Autowired
    private PositionInit positionService;

    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Position position = positionService.getPositionById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        Position newPosition = positionService.createPosition(position);
        return new ResponseEntity<>(newPosition, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable Long id, @RequestBody Position position) {
        positionService.updatePosition(id, position);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.softDeletePosition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
