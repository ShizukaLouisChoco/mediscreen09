package com.mediscreen.services.assessment.service;

import com.mediscreen.services.assessment.beans.NoteBean;
import com.mediscreen.services.assessment.beans.PatientBean;
import com.mediscreen.services.assessment.entity.Assessment;
import com.mediscreen.services.assessment.proxy.NoteProxy;
import com.mediscreen.services.assessment.proxy.PatientProxy;
import com.mediscreen.services.assessment.service.impl.AssessmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.mediscreen.services.assessment.beans.PatientBean.Gender.F;
import static com.mediscreen.services.assessment.beans.PatientBean.Gender.M;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AssessmentServiceTest {

    @InjectMocks
    AssessmentServiceImpl assessmentService;

    @Mock
    PatientProxy patientProxy;

    @Mock
    NoteProxy noteProxy;

    PatientBean patientManOver30 = new PatientBean(1L, "test1","test1",LocalDate.of(1980,01,01),M,"address","phone");
    PatientBean patientManUnder30 = new PatientBean(1L, "test3","test3",LocalDate.of(2000,01,01),M,"address","phone");
    PatientBean patientWomanUnder30 = new PatientBean(1L, "test4","test4",LocalDate.of(2000,01,01),F,"address","phone");
    List<NoteBean> noteBeanWithO = List.of(new NoteBean("1",1L,"none", LocalDate.of(2023, 01,01)));
    List<NoteBean> noteBeanWith3 = List.of(new NoteBean("1",1L,"hémoglobine A1C, microalbumine, taille", LocalDate.of(2023, 01,01)));
    List<NoteBean> noteBeanWith6 = List.of(new NoteBean("1",1L,"hémoglobine A1C, microalbumine, taille, poids, fumeur, anormal", LocalDate.of(2023, 01,01)));
    List<NoteBean> noteBeanWith8 = List.of(new NoteBean("1",1L,"hémoglobine A1C, microalbumine, taille, poids, fumeur, anormal, cholestérol, vertige", LocalDate.of(2023, 01,01)));


    @Test
    public void assessmentOver30WithScore0IsNoneTest() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWithO);
        when(patientProxy.getPatient(1L)).thenReturn(patientManOver30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.None, result);


    }
    @Test
    public void testAssessmentManUnder30WithScoreOIsNone() {

        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWithO);
        when(patientProxy.getPatient(1L)).thenReturn(patientManUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.None, result);
    }

    @Test
    public void assessmentWomanUnder30WithScore0IsNone() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWithO);
        when(patientProxy.getPatient(1L)).thenReturn(patientWomanUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.None, result);
    }

    @Test
    public void assessmentOver30BWithScore3IsBorderline() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith3);
        when(patientProxy.getPatient(1L)).thenReturn(patientManOver30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.Borderline, result);
    }

    @Test
    public void assessmentOver30WithScore6IsInDanger() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith6);
        when(patientProxy.getPatient(1L)).thenReturn(patientManOver30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.In_danger, result);
    }


    @Test
    public void assessmentManUnder30WithScore3IsInDanger() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith3);
        when(patientProxy.getPatient(1L)).thenReturn(patientManUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.In_danger, result);
    }



    @Test
    public void assessmentWomanUnder30WithScore6IsInDanger() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith6);
        when(patientProxy.getPatient(1L)).thenReturn(patientWomanUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.In_danger, result);
    }

    @Test
    public void assessmentOver30WithScore8IsEarlyOnset() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith8);
        when(patientProxy.getPatient(1L)).thenReturn(patientManOver30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.Early_onset, result);
    }

    @Test
    public void assessmentManUnder30WithScore6IsEarlyOnset() {
        when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith6);
        when(patientProxy.getPatient(1L)).thenReturn(patientManUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.Early_onset, result);
    }
    @Test
    public void assessmentWomanUnder30WithScore8IsEarlyOnset() {
       when(noteProxy.getNoteByPatient(1L)).thenReturn(noteBeanWith8);
        when(patientProxy.getPatient(1L)).thenReturn(patientWomanUnder30);

        Assessment result = assessmentService.getAssessmentByPatientId(1L);

        assertEquals(Assessment.Early_onset, result);
    }

}
