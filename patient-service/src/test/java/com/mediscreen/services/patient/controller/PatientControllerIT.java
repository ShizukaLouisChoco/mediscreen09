package com.mediscreen.services.patient.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.services.patient.entity.Patient;
import com.mediscreen.services.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static com.mediscreen.services.patient.entity.Patient.Gender.F;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatientService patientService;


    @Test
    public void createPatientPostMappingTest() throws Exception {
        //GIVEN
        final String url = "/patients";
        Patient testPatient = new Patient(100L,"TestPatient","TestPatient",LocalDate.of(2000,01,01),F,"Address","phone");

        //WHEN
        final var result = mockMvc.perform(post("/patients")
                .content(asJsonString(testPatient))
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.family").value(testPatient.getFamily()))
                .andExpect(jsonPath("$.given").value(testPatient.getGiven()))
                .andExpect(jsonPath("$.dob").value(testPatient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(testPatient.getAddress()))
                .andExpect(jsonPath("$.phone").value(testPatient.getPhone()));

    }



    @Test
    public void createPatientFormTest() throws Exception {
        //GIVEN
        Patient testPatient = new Patient();
        final String url = "/patient/add";

        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.family").value(testPatient.getFamily()))
                .andExpect(jsonPath("$.given").value(testPatient.getGiven()))
                .andExpect(jsonPath("$.dob").value(testPatient.getDob()))
                .andExpect(jsonPath("$.address").value(testPatient.getAddress()))
                .andExpect(jsonPath("$.phone").value(testPatient.getPhone()));
    }

     @Test
    public void getAllPatientsTest() throws Exception {
        //GIVEN
        final String url = "/patients";
        // WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4));

    }
    @Test
    public void getPatientTest() throws Exception {
        //GIVEN
        final String url = "/patients/{id}";
        Patient patient = patientService.getPatient(1L);
        //WHEN
        final var result = mockMvc.perform(get(url,1)
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.family").value(patient.getFamily()))
                .andExpect(jsonPath("$.given").value(patient.getGiven()))
                .andExpect(jsonPath("$.dob").value(patient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(patient.getAddress()))
                .andExpect(jsonPath("$.phone").value(patient.getPhone()));

    }

    @Test
    public void getPatientByFamilyTest() throws Exception {
        //GIVEN
        final String url = "/patients/family/{family}";
        Patient patient = patientService.getPatientByFamily("TestBorderline");
        // WHEN
        final var response = mockMvc.perform(get(url,patient.getFamily()))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.family").value(patient.getFamily()))
                .andExpect(jsonPath("$.given").value(patient.getGiven()))
                .andExpect(jsonPath("$.dob").value(patient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(patient.getAddress()))
                .andExpect(jsonPath("$.phone").value(patient.getPhone()));
    }


    @Test
    public void getPatientAndHistoryTest() throws Exception {
        //GIVEN
        final String url = "/patients/{id}/history";
        Patient patient = patientService.getPatient(1L);
        // WHEN
        final var response = mockMvc.perform(get(url,patient.getId()))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.family").value(patient.getFamily()))
                .andExpect(jsonPath("$.given").value(patient.getGiven()))
                .andExpect(jsonPath("$.dob").value(patient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(patient.getAddress()))
                .andExpect(jsonPath("$.phone").value(patient.getPhone()));
    }

    @Test
    public void updatePatientTest() throws Exception{
        //GIVEN
        final String url = "/patients/{id}";
        Patient patient = patientService.getPatient(1L);
        patient.setAddress("updatedAddress");
        // WHEN
        final var response = mockMvc.perform(put(url,patient.getId())
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON));


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.family").value(patient.getFamily()))
                .andExpect(jsonPath("$.given").value(patient.getGiven()))
                .andExpect(jsonPath("$.dob").value(patient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(patient.getAddress()))
                .andExpect(jsonPath("$.phone").value(patient.getPhone()));
    }

    @Test
    public void updatePatientFormTest() throws Exception{
        //GIVEN
        final String url = "/patients/update/{id}";
        Patient patient = patientService.getPatient(2L);
        // WHEN
        final var response = mockMvc.perform(get(url,patient.getId()))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.family").value(patient.getFamily()))
                .andExpect(jsonPath("$.given").value(patient.getGiven()))
                .andExpect(jsonPath("$.dob").value(patient.getDob().toString()))
                .andExpect(jsonPath("$.address").value(patient.getAddress()))
                .andExpect(jsonPath("$.phone").value(patient.getPhone()));

    }

    @Test
    public void deletePatientTest() throws Exception{
        //GIVEN
        final String url = "/patients/delete/{id}";
        Patient patient = patientService.getPatient(1L);
        // WHEN
        final var response = mockMvc.perform(get(url,patient.getId()))
                .andDo(MockMvcResultHandlers.print());


        // THEN
        response.andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
