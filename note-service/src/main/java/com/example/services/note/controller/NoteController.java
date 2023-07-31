package com.example.services.note.controller;

import com.example.services.note.entity.Note;
import com.example.services.note.service.NoteService;
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

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        log.info("Add new note");
        return noteService.addNote(note);
    }


    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Long id) {
        log.info("Get note with id = "+id);
        return noteService.getNoteById(id);
    }

    @GetMapping("/notes/patients/{id}")
    public List<Note> getNoteByPatient(@PathVariable Long id) {
        log.info("Get note with id patient = "+id);
        return noteService.getNoteByPatientId(id);
    }

    @GetMapping("/notes")
    public List<Note> getAllNote() {
        log.info("Get all note");
        return noteService.getAllNote();
    }

    @PutMapping("/notes")
    public Note updateNote(@RequestBody Note note) {
        log.info("Update note with id = "+ note.getId());
        return noteService.updateNote(note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        log.info("Delete note with id = "+id);
        noteService.deleteNoteById(id);
    }

    @DeleteMapping("/notes/patients/{id}")
    public void deleteNoteByPatientId(@PathVariable Long id){
        log.info("Delete note's Patient id = "+id);
        noteService.deleteNoteByPatientId(id);
    }
}
