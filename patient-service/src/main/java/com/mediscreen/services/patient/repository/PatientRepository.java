package com.mediscreen.services.patient.repository;

import com.mediscreen.services.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByFamilyAndGiven(String family, String given);
    Optional<List<Patient>> findPatientsByFamilyAndGiven(String family, String given);

    Optional<Patient> findPatientByFamily(String family);
}