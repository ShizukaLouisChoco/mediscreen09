package com.mediscreen.services.patient.service;

import com.mediscreen.services.patient.entity.Patient;
import com.mediscreen.services.patient.exception.PatientErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
