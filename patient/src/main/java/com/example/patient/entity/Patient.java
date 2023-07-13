package com.example.patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Table(name = "patients")
@Entity
public class Patient implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String family;

    @Column(nullable = false)
    private String given;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(nullable = false)
    private Gender sex;

    private String address;

    private String phone;

    public enum Gender {
        M,
        F
    }
    public Patient(String family, String given, LocalDate dob, Gender sex, String address, String phone){
        this.family = family;
        this.given = given;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }




}
