package com.example.patient.controller;

import com.example.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * GetMapping - Get person
     * url : http://localhost:8080/patient/get?patientId={patientId}
     * @return patient info with patientId
     */
    @GetMapping("/patient/get")
    public String getPatient(@RequestParam("patientId") final String patientId, Model model){
        log.info(".getPatient");
        log.info("Accessed endpoint URL:/persons");
        log.debug("Request details: GETMapping");

        model.addAttribute("patient",patientService.getPatient(Long.valueOf(patientId)));
        return "/patient";
    }

    /**
     * GetMapping - Get all person
     * url : http://localhost:8080/patient/all
     * @return list of all person
     */
    @GetMapping("/patient/all")
    public String getAllPatients(Model model){
        log.info(".getAllPersons");
        log.info("Accessed endpoint URL:/persons");
        log.debug("Request details: GETMapping");
        model.addAttribute("patients",patientService.getPatients());
        return "/patient";
    }
}
