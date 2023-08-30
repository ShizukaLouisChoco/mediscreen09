package com.mediscreen.services.note.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.services.note.entity.Note;
import com.mediscreen.services.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NoteService noteService;



    @Test
    public void getAllNoteTest() throws Exception{
        //GIVEN
        final String url = "/notes";

        //WHEN
        final var response = mockMvc.perform(get(url))
                .andDo(MockMvcResultHandlers.print());

        //THEN
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));

    }

    @Test
    public void getNoteByPatientIdTest() throws Exception{
        //GIVEN
        final String url = "/notes/patients/{patientId}";
        //WHEN
        final var result = mockMvc.perform(get(url,3L)
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    public void addNoteTest() throws Exception{
        //GIVEN
        final String url = "/notes";
        Note testNote = new Note("id",1L,"note", LocalDate.of(2000,01,01));

        //WHEN
        final var result = mockMvc.perform(post(url)
                .content(asJsonString(testNote))
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patientId").value(testNote.getPatientId()))
                .andExpect(jsonPath("$.note").value(testNote.getNote()))
                .andExpect(jsonPath("$.date").value(testNote.getDate().toString()));


    }


    @Test
    public void getNoteByIdTest() throws Exception{
        //GIVEN
        final String url = "/notes/{id}";
        Note testNote = noteService.getNoteById("1");
        //WHEN
        final var result = mockMvc.perform(get(url,testNote.getId())
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patientId").value(testNote.getPatientId()))
                .andExpect(jsonPath("$.note").value(testNote.getNote()))
                .andExpect(jsonPath("$.date").value(testNote.getDate().toString()));
    }



    @Test
    public void updateNoteTest() throws Exception{
        //GIVEN
        final String url = "/notes/{id}";
        Note testNote = noteService.getNoteById("3");
        testNote.setNote("updatedNote");

        //WHEN
        final var result = mockMvc.perform(put(url,testNote.getId())
                .content(asJsonString(testNote))
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.patientId").value(testNote.getPatientId()))
                .andExpect(jsonPath("$.note").value(testNote.getNote()))
                .andExpect(jsonPath("$.date").value(testNote.getDate().toString()));

    }


    @Test
    public void deleteNoteTest() throws Exception{
        //GIVEN
        final String url = "/notes/delete/{id}";
        Note testNote = noteService.getNoteById("1");
        // WHEN
        final var response = mockMvc.perform(get(url,testNote.getId()))
                .andDo(MockMvcResultHandlers.print());

        // THEN
        response.andExpect(status().isOk());

    }

    @Test
    public void deleteNoteByPatientId()throws Exception{
        //GIVEN
        final String url = "/notes/patients/delete/{patientId}";
        // WHEN
        final var response = mockMvc.perform(get(url,1L))
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
