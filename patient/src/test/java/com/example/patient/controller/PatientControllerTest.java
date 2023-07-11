package com.example.patient.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

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


}
