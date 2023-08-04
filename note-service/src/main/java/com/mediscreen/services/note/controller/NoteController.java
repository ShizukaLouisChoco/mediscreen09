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

    @GetMapping("/notes/all")
    public List<Note> getAllNote() {
        log.info("Get all note");
        return noteService.getAllNote();
    }
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable String id) {
        log.info("Get note with id = "+id);
        return noteService.getNoteById(id);
    }

    @GetMapping("/notes/patients/{patientId}")
    public List<Note> getNoteByPatientId(@PathVariable("patientId") Long patientId) {
        log.info("Get note with id patient = " + patientId);
        return noteService.getNoteByPatientId(patientId);
    }

    @PutMapping("/notes")
    public Note updateNote(@RequestBody Note note) {
        log.info("Update note with id = "+ note.getId());
        return noteService.updateNote(note);
    }

    @GetMapping("/notes/update/{id}")
    public Note updateNote(@PathVariable String id) {
        log.info("Update note form with id = " + id);
        return noteService.getNoteById(id);
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        log.info("Add new note");
        return noteService.addNote(note);
    }

    @GetMapping("/notes/add")
    public Note addNoteForm() {
        log.info("Add new note");
        return  new Note();
    }


    @DeleteMapping("/notes/{id}")
    public void deleteNoteById(@PathVariable String id) {
        log.info("Delete note with id = "+id);
        noteService.deleteNoteById(id);
    }
    @GetMapping("/notes/delete/{id}")
    public Note deleteNoteForm(@PathVariable String id) {
        log.info("Delete note form with id = " + id);
        return noteService.getNoteById(id);
    }

    @DeleteMapping("/notes/patients/{patientId}")
    public void deleteNoteByPatientId(@PathVariable Long patientId){
        log.info("Delete note's Patient id = "+ patientId);
        noteService.deleteNoteByPatientId(patientId);
    }
}
