package com.mediscreen.services.clientui.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
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

}
