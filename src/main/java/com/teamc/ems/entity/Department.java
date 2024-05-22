package com.teamc.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NonNull
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private Set<EMPUser> EMPUsers;

    @Column(name = "is_deleted")
    private boolean deleted = false;


}
