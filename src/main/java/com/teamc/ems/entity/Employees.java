package com.teamc.ems.entity;

import com.teamc.ems.user.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @NonNull
    private String idNo;

    @NonNull
    private String workRole;

    @NonNull
    private String department;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @NonNull
    private String address;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String image;

    @NonNull
    private String gender;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    private String emergencyContactName;

    @NonNull
    private String emergencyContactRelationship;

    @NonNull
    private String emergencyContactNo;

    @NonNull
    private Role role;



}
