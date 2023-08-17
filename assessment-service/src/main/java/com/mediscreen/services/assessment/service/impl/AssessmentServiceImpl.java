package com.mediscreen.services.assessment.service.impl;

import com.mediscreen.services.assessment.beans.NoteBean;
import com.mediscreen.services.assessment.beans.PatientBean;
import com.mediscreen.services.assessment.entity.Assessment;
import com.mediscreen.services.assessment.exception.AssessmentErrorException;
import com.mediscreen.services.assessment.proxy.NoteProxy;
import com.mediscreen.services.assessment.proxy.PatientProxy;
import com.mediscreen.services.assessment.service.AssessmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {


    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;

    public AssessmentServiceImpl(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    @Override
    public PatientBean getPatient(Long patientId){
        return patientProxy.getPatient(patientId);
    }
    @Override
    public PatientBean getPatientByFamily(String family){
        return patientProxy.getPatientByFamily(family);
    }

    @Override
    public Assessment getAssessmentByPatientId(Long id) {
        return assessPatient(patientProxy.getPatient(id));
    }

    @Override
    public Assessment getAssessmentByFamilyName(String familyName) {
        return assessPatient(patientProxy.getPatientByFamily(familyName));
    }

    @Override
    public int calculateAge(PatientBean patient) {
        return Period.between(patient.getDob(),  LocalDate.now()).getYears();
    }

    public Assessment assessPatient(PatientBean patient){
        int age = calculateAge(patient);
        PatientBean.Gender gender = patient.getSex();
        long triggerScore = countKeywords(patient.getId());


        // Man under 30
        if (gender.equals(PatientBean.Gender.M) && age < 30) {

            if (triggerScore <= 2) {
                return Assessment.None;
            }

            if (triggerScore <= 4) {
                return Assessment.In_danger;
            }

            return Assessment.Early_onset;
        }

        // woman under 30
        if (gender.equals(PatientBean.Gender.F) && age < 30) {

            if (triggerScore <= 3) {
                return Assessment.None;
            }

            if (triggerScore <= 6) {
                return Assessment.In_danger;
            }

            return Assessment.Early_onset;
        }

        // over 30
        if (age >= 30) {

            if (triggerScore <= 1) {
                return Assessment.None;
            }

            if (triggerScore <= 5) {
                return Assessment.Borderline;
            }

            if (triggerScore <= 7) {
                return Assessment.In_danger;
            }

            return Assessment.Early_onset;
        }

        new AssessmentErrorException("Failed to complete assessment with this patient id : " + patient.getId());

        return Assessment.Error;
    }

    private long countKeywords(Long id) {
        int count = 0;
        List<String> notes = noteProxy.getNoteByPatient(id).stream().map(NoteBean::getNote).collect(Collectors.toList());
        for (String note : notes) {
            count += countKeywordsInString(note, keywords);
        }
        return count;

    }

    public static int countKeywordsInString(String input, String[] keywords) {
        int count = 0;
        for (String keyword : keywords) {
            int index = input.indexOf(keyword);
            while (index != -1) {
                count++;
                index = input.indexOf(keyword, index + keyword.length());
            }
        }
        return count;
    }

    String[] keywords = {"hémoglobine A1C", "Hémoglobine A1C","microalbumine","Microalbumine", "taille","Taille", "poids", "Poids", "fumeur", "Fumeur", "anormal","Anormal", "cholestérol", "Cholestérol", "vertige", "Vertige", "rechute", "Rechute", "réaction","Réaction", "anticorps", "Anticorps"};

}
