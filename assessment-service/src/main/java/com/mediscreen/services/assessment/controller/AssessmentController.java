package com.mediscreen.services.assessment.controller;

import com.mediscreen.services.assessment.beans.PatientBean;
import com.mediscreen.services.assessment.entity.Assessment;
import com.mediscreen.services.assessment.service.AssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentController {

    private final AssessmentService assessmentService;





    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }


    @PostMapping("/assess/id")
    public ResponseEntity<String> assessById(@RequestParam("patId") Long patientId) {
        PatientBean patientToAssess = assessmentService.getPatient(patientId);
        Assessment assessmentResult =  assessmentService.getAssessmentByPatientId(patientId);

        String responseMessage = "Patient:" +  patientToAssess.getFamily() + " " + patientToAssess.getGiven() + "(age" + assessmentService.calculateAge(patientToAssess) + ") diabetes assessment is:" + assessmentResult;

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/assess/familyName")
    public ResponseEntity<String> assessByFamilyName(@RequestParam("familyName") String familyName) {
        PatientBean patientToAssess = assessmentService.getPatientByFamily(familyName);
        Assessment assessmentResult =  assessmentService.getAssessmentByFamilyName(familyName);

        String responseMessage = "Patient:" +  patientToAssess.getFamily() + " " + patientToAssess.getGiven() + "(age" + assessmentService.calculateAge(patientToAssess) + ") diabetes assessment is:" + assessmentResult;

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
