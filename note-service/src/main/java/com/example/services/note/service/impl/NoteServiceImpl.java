package com.example.services.note.service.impl;

import com.example.services.note.entity.Note;
import com.example.services.note.exception.NoteErrorException;
import com.example.services.note.repository.NoteRepository;
import com.example.services.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) {
            log.info("Note with this id = " + id + " not found");
            throw new NoteErrorException("Note not found with this id : "+ id);
        }
        log.info("Get patient with id =" + id + " from dataBase");
        return optionalNote.get();
    }

    @Override
    public List<Note> getNoteByPatientId(Long id) {
        log.info("Get all notes from database with patient id = "+id);
        return noteRepository.findAllByPatientIdOrderByDateDesc(id);
    }

    @Override
    public List<Note> getAllNote() {
        log.info("Get all notes from database");
        return noteRepository.findAll();
    }

    @Override
    public Note updateNote(Note note) {
        Optional<Note> optionalNote = noteRepository.findById(note.getId());

        if (optionalNote.isEmpty()) {
            log.info("Note with this id = " + note.getId() + " not found");
            throw new NoteErrorException("Not found note with this id");
        }

        log.info("Update note with id ="+note.getId()+" to dataBase");

        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) {
            log.info("Note with this id = " + id + " not found");
            throw new NoteErrorException("Not found note with this id");
        }

        noteRepository.deleteById(id);
        log.info("Delete note with id ="+id+" to dataBase");
    }

    @Override
    public void deleteNoteByPatientId(Long id) {
        noteRepository.deleteNoteByPatientId(id);
    }
}
