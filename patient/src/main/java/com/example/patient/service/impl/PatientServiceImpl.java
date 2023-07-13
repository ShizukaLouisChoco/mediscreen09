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

    @Override
    @Transactional
    public Patient updatePatient(Patient patient) {
        Optional<Patient> patientInDataBase =patientRepository.findById(patient.getId());
        Patient patientToUpdate = patientInDataBase.orElseThrow();
        patientToUpdate.setFamily(patient.getFamily());
        patientToUpdate.setGiven(patient.getGiven());
        patientToUpdate.setDob(patient.getDob());
        patientToUpdate.setSex(patient.getSex());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());
        return patientRepository.save(patientToUpdate);

    }
    @Override
    @Transactional
    public Patient createPatient(Patient patient) throws PatientErrorException {
        Optional<Patient> patientExists = patientRepository.findById(patient.getId());
        if(patientExists.isPresent()){
            throw new PatientErrorException("Patient already registered with id number : " + patient.getId());
        }

        Patient newPatient = new Patient(patient.getId(),patient.getFamily(),patient.getGiven(),patient.getDob(),patient.getSex(),patient.getAddress(),patient.getPhone());
        return patientRepository.save(newPatient);
    }
}
