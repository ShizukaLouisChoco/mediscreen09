package com.mediscreen.services.note.service;

import com.mediscreen.services.note.entity.Note;

import java.util.List;

public interface NoteService {

    //CREATE
    Note addNote(Note note);

    //READ
    Note getNoteById(String id);

    //READ BY PATIENT ID
    List<Note> getNoteByPatientId(Long id);

    //READ ALL
    List<Note> getAllNote();

    //UPDATE
    Note updateNote(String id, Note note);

    //DELETE
    void deleteNoteById(String id);

    void deleteNoteByPatientId(Long id);
}
