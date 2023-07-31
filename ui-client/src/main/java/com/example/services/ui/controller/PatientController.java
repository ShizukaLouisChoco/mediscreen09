package com.example.services.ui.controller;

import com.example.services.ui.beans.PatientBean;
import com.example.services.ui.proxies.NoteProxy;
import com.example.services.ui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;


    public PatientController(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    @GetMapping("/")
    public String accueil(Model model){
         model.addAttribute("patients",patientProxy.getAllPatients());
        return "patientPage";
    }
    @GetMapping("/patients/{id}")
    public String getPatient(@PathVariable Long id, Model model){
        model.addAttribute("patient",patientProxy.getPatient(id));
        return "patientPage";
    }

    @GetMapping("/patients/{id}/history")
    public String getPatientHistory(@PathVariable Long id, Model model){
        model.addAttribute("patient",patientProxy.getPatient(id));
        model.addAttribute("notes",noteProxy.getNoteByPatientId(id));
        return "patientPage";
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
