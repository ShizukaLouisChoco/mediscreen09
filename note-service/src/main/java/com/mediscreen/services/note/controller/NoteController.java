package com.mediscreen.services.note.controller;

import com.mediscreen.services.note.entity.Note;
import com.mediscreen.services.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    //CREATE
    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) throws Exception{
        log.info("Add new note");
        return noteService.addNote(note);
    }

    //CREATE FORM
    @GetMapping("/notes/add")
    public Note addNoteForm() {
        log.info("Add new note");
        return  new Note();
    }

    //READ ALL
    @GetMapping("/notes")
    public List<Note> getAllNote() {
        log.info("Get all note");
        return noteService.getAllNote();
    }

    //READ ONE
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable String id) {
        log.info("Get note with id = "+id);
        return noteService.getNoteById(id);
    }

    //READ ALL OF ONE
    @GetMapping("/notes/patients/{patientId}")
    public List<Note> getNoteByPatientId(@PathVariable("patientId") Long patientId) {
        log.info("Get note with id patient = " + patientId);
        return noteService.getNoteByPatientId(patientId);
    }

    //UPDATE
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable String id,@RequestBody Note note) throws Exception{
        log.info("Update note with id = "+ note.getId());
        return noteService.updateNote(id, note);
    }

    //UPDATE FORM
    @GetMapping("/notes/update/{id}")
    public Note updateNoteForm(@PathVariable String id) {
        log.info("Update note form with id = " + id);
        return noteService.getNoteById(id);
    }

    //DELETE ONE
    @GetMapping("/notes/delete/{id}")
    public void deleteNoteForm(@PathVariable String id) {
        log.info("Delete note form with id = " + id);
        noteService.deleteNoteById(id);
    }

    //DELETE ALL OF ONE
    @GetMapping("/notes/patients/delete/{patientId}")
    public void deleteNoteByPatientId(@PathVariable Long patientId){
        log.info("Delete note's Patient id = "+ patientId);
        noteService.deleteNoteByPatientId(patientId);
    }
}
