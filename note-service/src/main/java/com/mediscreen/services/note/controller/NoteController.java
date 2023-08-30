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
    /**
     * PostMapping - Crate new note
     * url : http://localhost:9100/notes
     * @return The created note
     */
    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) throws Exception{
        log.info("Add new note");
        return noteService.addNote(note);
    }

    //READ ALL
    /**
     * GetMapping - Read all notes
     * url : http://localhost:9100/notes
     * @return List of all notes
     */
    @GetMapping("/notes")
    public List<Note> getAllNote() {
        log.info("Get all note");
        return noteService.getAllNote();
    }

    //READ ONE
    /**
     * GetMapping - Read one note
     * url : http://localhost:9100/notes/{id}
     * @return One note with {id}
     */
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable String id) {
        log.info("Get note with id = "+id);
        return noteService.getNoteById(id);
    }

    //READ ALL OF ONE
    /**
     * GetMapping - Read all note of one patient
     * url : http://localhost:9100/notes/patients/{patientId}
     * @return All note of one patient with {patientId}
     */
    @GetMapping("/notes/patients/{patientId}")
    public List<Note> getNoteByPatientId(@PathVariable("patientId") Long patientId) {
        log.info("Get note with id patient = " + patientId);
        return noteService.getNoteByPatientId(patientId);
    }

    //UPDATE
    /**
     * PutMapping - Update one note
     * url : http://localhost:9100/notes/{id}
     * @return Updated note with {id}
     */
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable String id,@RequestBody Note note) throws Exception{
        log.info("Update note with id = "+ note.getId());
        return noteService.updateNote(id, note);
    }

    //DELETE ONE
    /**
     * GetMapping - Delete one note
     * url : http://localhost:9100/notes/delete/{id}
     */
    @GetMapping("/notes/delete/{id}")
    public void deleteNote(@PathVariable String id) {
        log.info("Delete note form with id = " + id);
        noteService.deleteNoteById(id);
    }

    //DELETE ALL OF ONE
    /**
     * GetMapping - Delete all notes of patient wirh {id}
     * url : http://localhost:9100/notes/patients/delete/{patientId}
     */
    @GetMapping("/notes/patients/delete/{patientId}")
    public void deleteNoteByPatientId(@PathVariable Long patientId){
        log.info("Delete note's Patient id = "+ patientId);
        noteService.deleteNoteByPatientId(patientId);
    }
}
