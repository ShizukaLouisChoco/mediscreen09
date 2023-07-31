package com.example.services.ui.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteBean {
    private Long id;
    @NotNull
    private Long patientId;
    @NotBlank(message = "Cant be empty")
    private String note;

    private LocalDate date;
}
