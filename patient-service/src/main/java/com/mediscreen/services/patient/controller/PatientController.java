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
     * GetMapping - Get all patients
     * url : http://localhost:8080/patients
     * @return list of all patients
     */
    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        log.info(".getAllPatients");
        log.info("Accessed endpoint URL:/patients");
        log.debug("Request details: GETMapping");
        //model.addAttribute("patients",patientService.getPatients());
        //return "/patient";

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

        //model.addAttribute("patient",patientService.getPatient(Long.valueOf(patientId)));
        return Optional.of(patientService.getPatient(id));
    }

    /**
     * GetMapping - Update an existing patient page
     * url : http://localhost:8080/patient/update/{patientId}
     * @return The form to update patient
     */
    /*@GetMapping("/update/{patientId}")
    public String updatePatientPage(@PathVariable("patientId") Long patientId, Model model) {
        log.info(".updatePatientPage");
        log.info("Accessed endpoint URL:/patient/update/{patientId}");
        model.addAttribute("patient", Optional.of(patientService.getPatient(patientId)).orElseThrow(()->new PatientErrorException("No patient with this id : "+patientId)));
        return "/update";
    }*/

    /**
     * PostMapping - Update an existing patient
     * url : http://localhost:8080/patient/update/{patientId}
     * @return The updated patient updated
     */
    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) throws PatientErrorException {
        log.info("heading to /patients/{id} post mapping");
        //model.addAttribute("patient",new Patient());
        patientService.updatePatient(patient);
        log.info("patient information updated");

        return patient;
    }

    /**
     * GetMapping - add a new patient form
     * url : http://localhost:8080/patient/add
     * @return The saved patient object
     */
    /*@GetMapping(value = "/add")
    public String createPatientForm(Model model){
        log.info(".createPerson");
        log.info("Accessed endpoint URL:/patient/add");
        model.addAttribute("patient",new Patient());
        return "/add";
    }*/

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
        //model.addAttribute("patient", patient);
        ;
        return patientService.createPatient(patient);
    }
}
