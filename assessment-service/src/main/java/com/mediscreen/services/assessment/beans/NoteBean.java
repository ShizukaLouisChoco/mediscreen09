package com.mediscreen.services.assessment.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteBean implements Serializable {
    private String id;

    private Long patientId;

    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


    public String getNote() {
        return note;
    }
}

