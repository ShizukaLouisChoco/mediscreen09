package com.mediscreen.services.patient.controller;

import com.mediscreen.services.patient.entity.Patient;
import com.mediscreen.services.patient.exception.PatientErrorException;
import com.mediscreen.services.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * PostMapping - add a new patient
     * url : http://localhost:8080/patient/add
     * @param patient to add
     * @return The saved patient object
     */
    @PostMapping(value = "/patients")
    public Patient createPatient(@RequestBody Patient patient){
        log.info(".createPerson");
        log.info("Accessed endpoint URL:/patient");
        log.debug("Request details: POSTMapping, Body patient :{}", patient);
        return patientService.createPatient(patient);
    }

    /**
     * GetMapping - add a new patient form
     * url : http://localhost:8080/patient/add
     * @return The saved patient object
     */
    @GetMapping(value = "/patient/add")
    public Patient createPatientForm(){
        log.info(".createPerson");
        log.info("Accessed endpoint URL:/patient/add");
        return new Patient();
    }


    /**
     * GetMapping - Get all patients
     * url : http://localhost:8080/patients
     * @return list of all patients
     */
    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        log.info(".getAllPatients");
        log.info("Accessed endpoint URL:/patients");
        log.debug("Request details: GETMapping");

        return patientService.getPatients();
    }

    /**
     * GetMapping - Get patient
     * url : http://localhost:8080/patients/{id}
     * @return patient info with patientId
     */
    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatient(@PathVariable Long id){
        log.info(".getPatient");
        log.info("Accessed endpoint URL:/patient/get");
        log.debug("Request details: GETMapping");

        return Optional.of(patientService.getPatient(id));
    }

    /**
     * GetMapping - Get patient
     * url : http://localhost:8080/patients/{id}
     * @return patient info with patientId
     */
    @GetMapping("/patients/{id}/history")
    public Optional<Patient> getPatientAndHistory(@PathVariable Long id){
        log.info(".getPatient");
        log.info("Accessed endpoint URL:/patient/get");
        log.debug("Request details: GETMapping");

        return Optional.of(patientService.getPatient(id));
    }

    /**
     * PutMapping - Update an existing patient
     * url : http://localhost:8080/patients/{id}
     * @return The updated patient updated
     */
    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) throws PatientErrorException {
        log.info("heading to /patients/{id} put mapping");
        Patient updatePatient = patientService.updatePatient(id, patient);
        log.info("patient information updated");

        return updatePatient;
    }
    /**
     * GetMapping - form to Update an existing patient
     * url : http://localhost:8080/patient/update/{patientId}
     * @return The form to update patient
     */
    @GetMapping("/patients/update/{id}")
    public Patient updatePatientForm(@PathVariable("id") Long id) {
        log.info(".updatePatientForm");
        log.info("Accessed endpoint URL:/patient/update/{patientId}");
        return patientService.getPatient(id);
    }

    @GetMapping("patients/delete/{id}")
    public void deletePatient(@PathVariable("id") Long id){
        log.info(".deletePatient");
        patientService.deletePatient(id);
    }

}
