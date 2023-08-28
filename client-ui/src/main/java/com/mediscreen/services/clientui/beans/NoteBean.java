package com.mediscreen.services.clientui.beans;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NoteBean implements Serializable {
    private String id;

    @NotNull(message = "Patient id cannot be empty")
    private Long patientId;
    @NotBlank(message = "Note cannot be empty")
    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cannot be empty")
    private LocalDate date;

}
