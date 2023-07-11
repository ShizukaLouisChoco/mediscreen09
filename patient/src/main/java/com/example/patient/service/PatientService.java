package com.example.patient.service;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientService {
    Patient getPatient(Long patientId);

    List<Patient> getPatients();
}
