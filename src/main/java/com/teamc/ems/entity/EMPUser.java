package com.teamc.ems.entity;

import com.teamc.ems.user.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class EMPUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @NonNull
    private String idNumber;

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

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "positionId")
    private Position position;


}
