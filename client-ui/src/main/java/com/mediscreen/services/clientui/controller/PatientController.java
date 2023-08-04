package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.beans.PatientBean;
import com.mediscreen.services.clientui.proxy.NoteProxy;
import com.mediscreen.services.clientui.proxy.PatientProxy;
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

    @GetMapping("/patients")
    public String patientList(Model model){
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

    @GetMapping("/patients/update/{id}")
    public String updatePatientPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientProxy.getPatient(id));
        return "patientUpdate";
    }

    @PutMapping("/patients/{id}")
    public String updatePatient(@PathVariable Long id, PatientBean patient,Model model) {
        model.addAttribute("patient",patientProxy.getPatient(id));
        patientProxy.updatePatient(id,patient);
        return "redirect:/patients/" + id;
    }

    @GetMapping(value = "/patients/add")
    public String createPatientForm(Model model){
        model.addAttribute("patient",new PatientBean());
        return "patientAdd";
    }

    @PostMapping(value = "/patients")
    public String createPatient(PatientBean patient, Model model){
        model.addAttribute("patient", patient);
        patientProxy.createPatient(patient);
        return "redirect:/patients";

    }
}
