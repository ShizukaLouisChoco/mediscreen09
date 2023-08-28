package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.beans.PatientBean;
import com.mediscreen.services.clientui.proxy.AssessmentProxy;
import com.mediscreen.services.clientui.proxy.NoteProxy;
import com.mediscreen.services.clientui.proxy.PatientProxy;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class PatientController {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;

    private final AssessmentProxy assessmentProxy;


    public PatientController(PatientProxy patientProxy, NoteProxy noteProxy, AssessmentProxy assessmentProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
        this.assessmentProxy = assessmentProxy;
    }

    //CREATE
    @PostMapping(value = "/patients")
    public String createPatient(@Valid @ModelAttribute("patient") PatientBean patient, BindingResult result, Model model){
        log.info("postmapping /patients/add for createPatient()");

        model.addAttribute("patient", patient);
        if(result.hasErrors()){
           log.info("validation error");
           return "patientAdd";
        }

        try{
            log.info("creating user");
            patientProxy.createPatient(patient);
        }catch(Exception exception){
            model.addAttribute("patient", patient);
            log.error(exception.getMessage());
            model.addAttribute("errorMsg", exception.getMessage());
            return "patientAdd";
        }
        log.info("patient is created");

        return "redirect:/";

    }

    //CREATE FORM
    @GetMapping(value = "/patients/add")
    public String createPatientForm(Model model){
        log.info("getmapping /patients/add for createPatientForm()");
        model.addAttribute("patient",new PatientBean());
        return "patientAdd";
    }

    //READ ALL
    @GetMapping(value ="/")
    public String getAllPatients(Model model){
        log.info("getmapping / for gatAllPatients()");
        model.addAttribute("patients",patientProxy.getAllPatients());

        return "allPatients";
    }

    //READ ONE
    @GetMapping(value ="/patients/{id}")
    public String getPatient(@PathVariable Long id, Model model){
        log.info("getmapping /patients/{id} for getPatient()");
        model.addAttribute("patient",patientProxy.getPatient(id));
        model.addAttribute("notes",noteProxy.getNoteByPatientId(id));
        try{
            log.info("creating assessment by patient id : "+ id);
            String assessment = assessmentProxy.assessById(id).toString();
            model.addAttribute("assessment",assessment.substring(assessment.indexOf("diabetes assessment is:") + "diabetes assessment is:".length()).split(",")[0].trim());
        }catch(Exception exception){
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg",exception.getMessage());
            return "patientPage";
        }
        return "patientPage";
    }

    //READ ONE +
    @GetMapping(value = "/patients/{id}/history")
    public String getPatientHistory(@PathVariable Long id, Model model){
        log.info("getmapping /patients/{id}/history for getPatientHistory()");
        model.addAttribute("patient",patientProxy.getPatient(id));
        model.addAttribute("notes",noteProxy.getNoteByPatientId(id));
        return "patientPage";
    }


    //UPDATE
    @PostMapping(value ="/patients/update/{id}")
    public String updatePatient(@PathVariable("id") Long id, @Valid @ModelAttribute("patient") PatientBean patient, BindingResult result, Model model) {
        log.info("postmapping /patients for updatePatient()");
        model.addAttribute("patient", patient);

        if(result.hasErrors()){
            log.info("validation error : " + result.getAllErrors());
            return "patientUpdate";
        }try {
            log.info("updating patient");
            patientProxy.updatePatient(id, patient);
        }catch(Exception exception){
            model.addAttribute("patient", patient);
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg", exception.getMessage());
            return "patientUpdate";
        }
        log.info("patient is updated");
        return "redirect:/patients/" + id;
    }

    //UPDATE FORM
    @GetMapping(value = "/patients/update/{id}")
    public String updatePatientForm(@PathVariable("id") Long id, Model model) {
        log.info("getmapping /patients/update/{id} for updatePatientForm()");
        model.addAttribute("patient", patientProxy.getPatient(id));
        return "patientUpdate";
    }

    //DELETE ONE
    @GetMapping(value = "/patients/delete/{id}")
    String deletePatient(@PathVariable("id") Long id){
        log.info("getmapping /patients/delete/{id} for deletePatient()");
        patientProxy.deletePatient(id);
        return "redirect:/";
    }
}
