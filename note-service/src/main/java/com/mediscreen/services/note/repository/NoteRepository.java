package com.mediscreen.services.note.repository;

import com.mediscreen.services.note.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByPatientIdOrderByDateDesc(Long id);

    void deleteNoteByPatientId(Long id);
}
