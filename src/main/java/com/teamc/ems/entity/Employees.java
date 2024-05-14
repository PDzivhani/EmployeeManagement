package com.teamc.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private int birthday;

    @NonNull
    private int idNo;

    @NonNull
    private String workRole;

    @NonNull
    private String department;

    @CreatedDate
    private LocalDate startDate;

    @NonNull
    private String address;

    @NonNull
    private int phoneNumber;

    @NonNull
    private String image;

    @NonNull
    private String gender;

    @NonNull
    private Long password;

    @NonNull
    private String email;

    @NonNull
    private String emergencyContactName;

    @NonNull
    private String emergencyContactRelationship;

    @NonNull
    private int emergencyContactNo;



}
