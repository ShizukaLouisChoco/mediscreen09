package com.mediscreen.services.patient.service.impl;

import com.mediscreen.services.patient.entity.Patient;
import com.mediscreen.services.patient.exception.PatientErrorException;
import com.mediscreen.services.patient.repository.PatientRepository;
import com.mediscreen.services.patient.service.PatientService;
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
    public Patient getPatientByFamilyAndGiven(String family, String given) {
        return patientRepository.findPatientByFamilyAndGiven(family, given)
                .orElseThrow(()->new PatientErrorException("Patient not found with this name : " + family + given));
    }
    @Override
    public List<Patient> getPatientsByFamilyAndGiven(String family, String given) {
        return patientRepository.findPatientsByFamilyAndGiven(family, given)
                .orElseThrow(()->new PatientErrorException("Patient not found with id : " + family + given));
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        if(patients.isEmpty()){
            throw new PatientErrorException("No patient registered");
        }
        return patients;
    }

    @Override
    @Transactional
    public Patient updatePatient(Long id, Patient patient) {
        Patient patientToUpdate = patientRepository.findById(id).get();
        //Patient patientToUpdate = patientInDataBase.orElseThrow();
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
        Optional<List<Patient>> patientNameExistsList = patientRepository.findPatientsByFamilyAndGiven(patient.getFamily(),patient.getGiven());
        if(patientNameExistsList.isPresent() && patientNameExistsList.get().size() > 0){
            throw new PatientErrorException("This patient : " + patient.getFamily() + " " + patient.getGiven() + " is already registered");
        }
        Patient newPatient = new Patient(null,patient.getFamily(),patient.getGiven(),patient.getDob(),patient.getSex(),patient.getAddress(),patient.getPhone());
        return patientRepository.save(newPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientByFamily(String family) {
        return patientRepository.findPatientByFamily(family)
                .orElseThrow(()->new PatientErrorException("Patient not found with this name : " + family));
    }
}
