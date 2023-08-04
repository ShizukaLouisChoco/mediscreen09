package com.mediscreen.services.clientui.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientBean {

    private Long id;

    @NotBlank(message = "family name cannot be empty")
    private String family;

    @NotBlank(message = "given name cannot be empty")
    private String given;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "date of birth cannot be null")
    private LocalDate dob;

    @NotNull(message = "sex cannot be null")
    private Gender sex;

    private String address;

    private String phone;

    public enum Gender {
        M,
        F
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
