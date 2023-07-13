package com.example.patient.controller;

import com.example.patient.entity.Patient;
import com.example.patient.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientService patientService;


    @DisplayName("get patient displays patient.html")
    @Test
    public void testGetPatient() throws Exception {
        //GIVEN
        final String url = "/patient/get";

        // WHEN
        final var response = mockMvc.perform(get(url)
                .param("patientId","1"))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/patient"))
                .andExpect(model().attributeExists("patient"));
    }

    @DisplayName("get patients displays patient.html")
    @Test
    public void testGetPatients() throws Exception {
        //GIVEN
        final String url = "/patient/all";

        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/patient"))
                .andExpect(model().attributeExists("patients"));
    }


    @DisplayName("display patient form")
    @Test
    public void testCreatePatientForm() throws Exception {
        //GIVEN
        final String url = "/patient/add";

        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(view().name("/add"))
                .andExpect(model().attributeExists("patient"));
    }


    @DisplayName("get patients displays patient.html")
    @Test
    public void testCreatePatient() throws Exception {
        //GIVEN
        final String url = "/patient/add";
        Patient patient = new Patient("family","given", LocalDate.of(2002,01,01), Patient.Gender.M,"address","phone");

        // WHEN
        final var response = mockMvc.perform(post(url)
                .param("family",patient.getFamily())
                .param("given",patient.getGiven())
                .param("dob",patient.getDob().toString())
                .param("sex",patient.getSex().toString())
                .param("address",patient.getAddress())
                .param("phone",patient.getPhone())
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        // THEN
        Long patientId = patientService.getPatientByFamilyAndGiven(patient.getFamily(),patient.getGiven()).getId();
        response.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/patient/get?patientId=" + patientId));

    }
    @SneakyThrows
    protected String asJsonString(final Object obj) {
        return objectMapper.writeValueAsString(obj);
    }
}
