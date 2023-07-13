package com.example.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.patient.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByFamilyAndGiven(String family, String given);
    Optional<List<Patient>> findPatientsByFamilyAndGiven(String family, String given);
}