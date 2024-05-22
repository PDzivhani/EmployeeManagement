package com.teamc.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    @NonNull
    private String positionName;

    @NonNull
    private double salary;

    @ManyToMany(mappedBy = "positions")
    private Set<EMPUser> EMPUsers;

    @Column(name = "is_deleted")
    private boolean deleted = false;
}
