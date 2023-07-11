package com.example.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.patient.entity.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}