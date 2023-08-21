package com.mediscreen.services.assessment.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    private Long id;

    private String family;

    private String given;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

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

    public String getFamily() {
        return family;
    }

    public String getGiven() {
        return given;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Gender getSex() {
        return sex;
    }


}
