package com.example.patient.controller;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value="/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    /**
     * GetMapping - Get all patients
     * url : http://localhost:8080/patient/all
     * @return list of all patients
     */
    @GetMapping("/list")
    public String getAllPatients(Model model){
        log.info(".getAllPatients");
        log.info("Accessed endpoint URL:/patient/list");
        log.debug("Request details: GETMapping");
        model.addAttribute("patients",patientService.getPatients());
        return "/patient";
    }

    /**
     * GetMapping - Get patient
     * url : http://localhost:8080/patient/get?patientId={patientId}
     * @return patient info with patientId
     */
    @GetMapping("/get")
    public String getPatient(@RequestParam("patientId") final String patientId, Model model){
        log.info(".getPatient");
        log.info("Accessed endpoint URL:/patient/get");
        log.debug("Request details: GETMapping");

        model.addAttribute("patient",patientService.getPatient(Long.valueOf(patientId)));
        return "/patient";
    }

    /**
     * GetMapping - Update an existing patient page
     * url : http://localhost:8080/patient/update/{patientId}
     * @return The form to update patient
     */
    @GetMapping("/update/{patientId}")
    public String updatePatientPage(@PathVariable("patientId") Long patientId, Model model) {
        log.info(".updatePatientPage");
        log.info("Accessed endpoint URL:/patient/update/{patientId}");
        model.addAttribute("patient", Optional.of(patientService.getPatient(patientId)).orElseThrow(()->new PatientErrorException("No patient with this id : "+patientId)));
        return "/update";
    }

    /**
     * PostMapping - Update an existing patient
     * url : http://localhost:8080/patient/update/{patientId}
     * @return The updated patient updated
     */
    @PostMapping("/update/{patientId}")
    public String updatePatient(@PathVariable("patientId") Long patientId, Patient patient,
                                Model model) throws PatientErrorException {
        log.info("heading to /patient/update/{patientId} post mapping");
        model.addAttribute("patient",new Patient());
        patientService.updatePatient(patient);
        log.info("patient information updated");
        //return "redirect:/patient/list";
        return "redirect:/patient/get?patientId=" + patientId;
    }

    /**
     * GetMapping - add a new patient form
     * url : http://localhost:8080/patient/add
     * @return The saved patient object
     */
    @GetMapping(value = "/add")
    public String createPatientForm(Model model){
        log.info(".createPerson");
        log.info("Accessed endpoint URL:/patient/add");
        model.addAttribute("patient",new Patient());
        return "/add";
    }

    /**
     * PostMapping - add a new patient
     * url : http://localhost:8080/patient/add
     * @param patient to add
     * @return The saved patient object
     */
    @PostMapping(value = "/add")
    public String createPatient(Patient patient, Model model){
        log.info(".createPerson");
        log.info("Accessed endpoint URL:/patient");
        log.debug("Request details: POSTMapping, Body patient :{}", patient);
        model.addAttribute("patient", patient);
        patientService.createPatient(patient);
        List<Patient> patients = patientService.getPatientsByFamilyAndGiven(patient.getFamily(),patient.getGiven());
        if(patients.size()==1){
            Long registeredPatientId = patients.get(0).getId();
            return "redirect:/patient/get?patientId="+ registeredPatientId;
        }
        Patient registeredPatient = patients.stream().filter(p -> p.getDob().isEqual(patient.getDob())).findFirst().orElseThrow();
        return "redirect:/patient/get?patientId="+registeredPatient.getId();
    }
}
