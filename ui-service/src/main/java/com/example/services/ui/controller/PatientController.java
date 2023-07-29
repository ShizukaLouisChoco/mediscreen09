package com.example.services.ui.controller;

import com.example.services.ui.bean.PatientBean;
import com.example.services.ui.proxies.PatientProxy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    private final PatientProxy patientProxy;


    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/list")
    public String getAllPatients(Model model){
         model.addAttribute("patients",patientProxy.getAllPatients());
        return "/patient";
    }
    @GetMapping("/get")
    public String getPatient(@RequestParam("patientId") final String patientId, Model model){
        model.addAttribute("patient",patientProxy.getPatient(patientId));
        return "/patient";
    }

    @GetMapping("/update/{patientId}")
    public String updatePatientPage(@PathVariable("patientId") Long patientId, Model model) {
        model.addAttribute("patient", patientProxy.getPatient(patientId.toString()));
        return "/update";
    }

    @PostMapping("/update/{patientId}")
    public String updatePatient(@PathVariable("patientId") Long patientId, PatientBean patient,Model model) {
        model.addAttribute("patient",new PatientBean());
        patientProxy.updatePatient(patientId,patient);
       return "redirect:/patient/get?patientId=" + patientId;
    }

    @GetMapping(value = "/add")
    public String createPatientForm(Model model){
        model.addAttribute("patient",new PatientBean());
        return "/add";
    }

    @PostMapping(value = "/add")
    public String createPatient(PatientBean patient, Model model){
        model.addAttribute("patient", patient);
        patientProxy.createPatient(patient);
        return "redirect:/patient/get?patientId="+patient.getId();

    }
}
