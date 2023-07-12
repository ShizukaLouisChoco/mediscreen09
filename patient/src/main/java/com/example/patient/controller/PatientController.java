package com.example.patient.controller;

import com.example.patient.entity.Patient;
import com.example.patient.exception.PatientErrorException;
import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
