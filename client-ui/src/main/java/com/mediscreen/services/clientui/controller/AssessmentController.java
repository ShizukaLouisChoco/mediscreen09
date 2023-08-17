package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.proxy.AssessmentProxy;
import com.mediscreen.services.clientui.proxy.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class AssessmentController {

    private final AssessmentProxy assessmentProxy;
    private final PatientProxy patientProxy;

    public AssessmentController(AssessmentProxy assessmentProxy, PatientProxy patientProxy) {
        this.assessmentProxy = assessmentProxy;
        this.patientProxy = patientProxy;
    }

    //Post form assessment by patient id
    @GetMapping("/assess/id")
    public String assessByPatientIdForm(Model model) {
        log.info("getmapping /assess/id returns assessForm.html");

        model.addAttribute("patientId",null);

        return "assessForm";
    }

    //Post form assessment by patient family name
    @GetMapping("/assess/family")
    public String assessByPatientFamilyForm(Model model) {
        log.info("getmapping /assess/family returns assessForm.html");

        model.addAttribute("familyName", "");

        return "assessForm";
    }

    //Assessment By patient id
    @PostMapping("/assess/id")
    public String assessById(Long patientId, Model model) throws Exception {
        log.info("postmapping /assess/id for assessById()");
       try{
            log.info("creating assessment by patient id : "+ patientId);
            model.addAttribute("patient", patientProxy.getPatient(patientId));
            String assessment = assessmentProxy.assessById(patientId).toString();
            model.addAttribute("assessment",assessment.substring(assessment.indexOf("diabetes assessment is:") + "diabetes assessment is:".length()).split(",")[0].trim());
        }catch(Exception exception){
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg",exception.getMessage());
            return "assessResult";
        }
        return "assessResult";
    }


    //Assessment By patient family name
    @PostMapping("/assess/family")
    public String assessByFamilyName(@RequestParam("familyName") String familyName, Model model) throws Exception {
        log.info("postmapping /assess/family for assessByFamily()");
        try{
            log.info("creating assessment by patient family name : "+ familyName);
            model.addAttribute("patient", patientProxy.getPatientByFamily(familyName));
            model.addAttribute("assessment",assessmentProxy.assessByFamilyName(familyName));
        }catch(Exception exception){
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg",exception.getMessage());
            return "assessResult";
        }
        return "assessResult";
    }

}
