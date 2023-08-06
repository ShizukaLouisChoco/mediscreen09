package com.mediscreen.services.clientui.beans;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;

@Validated
public class NoteBean implements Serializable {
    private String id;

    @NotNull
    private Long patientId;
    @NotBlank(message = "Cannot be empty")
    private String note;

    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }}
