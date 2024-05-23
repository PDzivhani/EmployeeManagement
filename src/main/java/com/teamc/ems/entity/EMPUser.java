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
public class EMPUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NonNull
            //(message = "First name is required")
    private String firstName;

    @NonNull
    //@NotEmpty(message = "Last name is required")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @NonNull
    //@Size(min = 13, max = 13, message = "ID number must be 13 characters")
    private String idNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @NonNull
    //@NotEmpty(message = "Address is required")
    private String address;

    @NonNull
    //@NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @NonNull
    private String image;

    @NonNull
    //@NotEmpty(message = "Gender is required")
    private String gender;

    @NonNull
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
//            message = "Password must contain at least 8 characters, " +
//                    "one uppercase letter, one lowercase letter, one number," +
//                    " and one special character")
    private String password;

    @NonNull

//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NonNull
    private String emergencyContactName;


    @NonNull
            //(message = "Emergency contact relationship is required")
    private String emergencyContactRelationship;

    @NonNull
    //@NotEmpty(message = "Emergency contact number is required")
    private String emergencyContactNo;

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "employee_position",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<Position> positions;

    @ManyToOne
    private Department department;

}
