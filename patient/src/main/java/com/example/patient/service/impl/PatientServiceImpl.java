package com.example.patient.service.impl;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatient(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(()->new PatientErrorException("Patient not found with id : " + patientId));
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

}
