package com.teamc.ems.entity;

import com.teamc.ems.user.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Entity
public class EMPUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Valid



    @NotBlank
    //@NotEmpty(message= "name is required")
    private String firstName;

    @NotBlank
    //@NotEmpty(message= "lastname is required")
    private String lastName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    //@NotEmpty(message= "date of birth is required")
    private Date dateOfBirth;

    @NotBlank
    @Min(value = 13)
    @Digits(integer = 13,fraction = 0 )
    //@NotEmpty(message= "id number is required")
    private Integer idNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    //@NotEmpty(message= "start date is required")
    private Date startDate;

    @NotBlank
    //@NotEmpty(message= "address is required")
    private String address;

    @NotBlank
    @Digits(integer = 10,fraction = 0 )
    //@NotEmpty(message= "phone number is required")
    private Integer phoneNumber;

    @NotBlank
    private String image;

    @NotBlank
    //@NotEmpty(message= "gender is required")
    private String gender;

    @NotBlank
    @Size(min =8,max =16)
    //@NotEmpty(message= "password is required")
    private String password;



    @NotBlank
    @Email
    //@NotEmpty(message= "email is required")
    private String email;

    @NotBlank
    //@NotEmpty(message= "emergency contact name is required")
    private String emergencyContactName;

    @NotBlank
    //@NotEmpty(message= "emergency contact relationship is required")
    private String emergencyContactRelationship;

    @NotBlank
    @Digits(integer = 10,fraction = 0 )
    //@NotEmpty(message= "emergency contact number is required")
    private Integer emergencyContactNo;

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @NotBlank
    //@NotEmpty(message= "role is required")
    private Role role;

    @ManyToOne
    //@NotEmpty(message= "position is required")
    private Position position;

    @ManyToOne
    //@NotEmpty(message= "department is required")
    private Department department;

}
