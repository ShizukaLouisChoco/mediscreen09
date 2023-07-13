package com.example.patient.service;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient getPatient(Long patientId);

    Patient getPatientByFamilyAndGiven(String family, String given);

    List<Patient> getPatientsByFamilyAndGiven(String family, String given);

    List<Patient> getPatients();

    @Transactional
    Patient updatePatient(Patient patient);

    @Transactional
    Patient createPatient(Patient patient) throws PatientErrorException;
}
