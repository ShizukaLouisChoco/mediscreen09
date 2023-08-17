package com.mediscreen.services.assessment.service;

import com.mediscreen.services.assessment.beans.PatientBean;
import com.mediscreen.services.assessment.entity.Assessment;

public interface AssessmentService {

    Assessment getAssessmentByPatientId(Long patientId);

    Assessment getAssessmentByFamilyName(String patientName);

    int calculateAge(PatientBean patient);

    PatientBean getPatient(Long patientId);

    PatientBean getPatientByFamily(String family);
}
