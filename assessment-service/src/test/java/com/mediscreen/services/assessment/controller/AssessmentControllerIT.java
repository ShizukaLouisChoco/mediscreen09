package com.mediscreen.services.assessment.controller;

import com.mediscreen.services.assessment.beans.NoteBean;
import com.mediscreen.services.assessment.beans.PatientBean;
import com.mediscreen.services.assessment.proxy.NoteProxy;
import com.mediscreen.services.assessment.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class AssessmentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientProxy patientProxy;

    @MockBean
    NoteProxy noteProxy;

    @Test
    public void assessmentByPatientIdTest()throws Exception{
        //GIVEN
        PatientBean testPatient = new PatientBean(1L, "Test","Test", LocalDate.of(2000,01,01), PatientBean.Gender.M,"Address","Phone");
        List<NoteBean> testList = new ArrayList<>();
        NoteBean testNote = new NoteBean("test1",1L, "testNote",LocalDate.of(2020,01,01));
        testList.add(testNote);
        when(patientProxy.getPatient(testPatient.getId())).thenReturn(testPatient);
        when(noteProxy.getNoteByPatient(testPatient.getId())).thenReturn(testList);
         //WHEN
        final var response = mockMvc.perform(post("/assess/id")
                .param("patId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Patient:Test Test(age23) diabetes assessment is:None"));

    }
    @Test
    public void assessmentByPatientNameTest()throws Exception{
        //GIVEN
        PatientBean testPatient = new PatientBean(1L, "Test","Test", LocalDate.of(2000,01,01), PatientBean.Gender.M,"Address","Phone");
        when(patientProxy.getPatientByFamily(testPatient.getFamily())).thenReturn(testPatient);
        List<NoteBean> testList = new ArrayList<>();
        NoteBean testNote = new NoteBean("test1",1L, "testNote",LocalDate.of(2020,01,01));
        testList.add(testNote);
        when(noteProxy.getNoteByPatient(testPatient.getId())).thenReturn(testList);


        //WHEN
        final var response = mockMvc.perform(post("/assess/familyName")
                .param("familyName", testPatient.getFamily())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("Patient:Test Test(age23) diabetes assessment is:None"));

    }

}
