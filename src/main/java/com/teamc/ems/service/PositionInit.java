package com.teamc.ems.service;

import com.teamc.ems.entity.Position;

import java.util.List;

public interface PositionInit {
    List<Position> getAllPositions();

    Position getPositionById(Long id);

    Position createPosition(Position position);

    void savePositions(Position position);

    void updatePosition(Long id, Position position);

    void softDeletePosition(Long id);
}
