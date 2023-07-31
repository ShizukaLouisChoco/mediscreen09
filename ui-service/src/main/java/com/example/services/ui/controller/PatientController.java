package com.example.services.ui.controller;

import com.example.services.ui.beans.PatientBean;
import com.example.services.ui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientProxy patientProxy;


    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @RequestMapping("/")
    public String accueil(Model model){
         model.addAttribute("patients",patientProxy.getAllPatients());
        return "/patient";
    }
    @GetMapping("/patient-service/{id}")
    public String getPatient(@PathVariable Long id, Model model){
        model.addAttribute("patient",patientProxy.getPatient(id));
        return "/patient";
    }

    /*@GetMapping("/pa/{id}")
    public String updatePatientPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientProxy.getPatient(id));
        return "/update";
    }*/

    @PutMapping("/patients/{id}")
    public String updatePatient(@PathVariable Long id, PatientBean patient,Model model) {
        model.addAttribute("patient",new PatientBean());
        patientProxy.updatePatient(id,patient);
       return "redirect:/patients/" + id;
    }

    /*@GetMapping(value = "/add")
    public String createPatientForm(Model model){
        model.addAttribute("patient",new PatientBean());
        return "/add";
    }*/

    @PostMapping(value = "/patients")
    public String createPatient(@RequestBody PatientBean patient, Model model){
        model.addAttribute("patient", patient);
        patientProxy.createPatient(patient);
        return "redirect:/patient/get?patientId="+patient.getId();

    }
}
