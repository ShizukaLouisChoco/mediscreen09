package com.mediscreen.services.note.service;


import com.mediscreen.services.note.entity.Note;
import com.mediscreen.services.note.exception.NoteErrorException;
import com.mediscreen.services.note.repository.NoteRepository;
import com.mediscreen.services.note.service.impl.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @InjectMocks
    NoteServiceImpl  noteService;

    @Mock
    NoteRepository noteRepository;

    @Test
    public void getNoteTest(){
        // GIVEN
        final Note registeredNote = new Note("1",1L,"Note1", LocalDate.of(1966,12,31));

        // WHEN
        when(noteRepository.findById(any())).thenReturn(Optional.of(registeredNote));

        Note result = noteService.getNoteById(registeredNote.getId());

        // THEN
        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(registeredNote));

    }

    @Test
    public void getNoteWithExceptionTest(){
        // GIVEN
        final Note registeredNote = new Note("1",1L,"Note1", LocalDate.of(1966,12,31));

        // WHEN
        when(noteRepository.findById(any())).thenReturn(Optional.empty());
        // THEN
        assertThatThrownBy(() -> {
            noteService.getNoteById(registeredNote.getId());
        })
                .isInstanceOf(NoteErrorException.class)
                .hasMessageContaining("Note not found with this id : "+ registeredNote.getId());
    }

    @Test
    public  void getAllNotesTest(){
        //GIVEN

        //WHEN
        var result = noteService.getAllNote();

        //THEN
        verify(noteRepository,times(1)).findAll();


    }
    @Test
    public void updatePatientTest(){
        // GIVEN
        final Note registeredNote = new Note("1",1L,"Note1", LocalDate.of(1966,12,31));
        final Note updateingNote = new Note("1",1L,"UpdatedNote1", LocalDate.of(2002,12,31));
// WHEN
        when(noteRepository.findById(registeredNote.getId())).thenReturn(Optional.of(registeredNote));
        when(noteRepository.save(any(Note.class))).thenAnswer(r -> r.getArguments()[0]);

        var result = noteService.updateNote(updateingNote.getId(),updateingNote);

        // THEN
        verify(noteRepository,times(1)).save(any(Note.class));

        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg).isEqualTo(updateingNote));
    }

    @Test
    public void updateNoteThrowsExceptionTest(){
        //GIVEN
        Optional<Note> noteOptional = Optional.empty();
        Note note = new Note();
        //WHEN
        when(noteRepository.findById(any())).thenReturn(noteOptional);

        //THEN
        assertThatThrownBy(() -> {
            noteService.updateNote("test",note);
        })
                .isInstanceOf(NoteErrorException.class)
                .hasMessageContaining("Not found note with this id");
    }
    @Test
    public void createNoteTest(){
        //GIVEN
        final Note newNote = new Note(null,1L,"Note1", LocalDate.of(1966,12,31));
        when(noteRepository.save(any())).thenAnswer(p -> p.getArguments()[0]);

        //WHEN
        Note result = noteService.addNote(newNote);

        //THEN
        verify(noteRepository,times(1)).save(any(Note.class));
        assertThat(result)
                .isNotNull()
                .satisfies(arg -> assertThat(arg.getPatientId()).isEqualTo(newNote.getPatientId()))
                .satisfies(arg -> assertThat(arg.getDate()).isEqualTo(newNote.getDate()))
                .satisfies(arg -> assertThat(arg.getNote()).isEqualTo(newNote.getNote()));
    }


    @Test
    public void deleteNoteTest(){
        //GIVEN
        final Note registeredNote = new Note("10",1L,"Note1", LocalDate.of(1966,12,31));
        when(noteRepository.findById(registeredNote.getId())).thenReturn(Optional.of(registeredNote));

        //WHEN
        noteService.deleteNoteById(registeredNote.getId());

        //THEN
        verify(noteRepository,times(1)).deleteById(registeredNote.getId());
    }

    @Test
    public void deleteNoteThrowsExceptionTest(){
        //GIVEN
        Optional<Note> noteOptional = Optional.empty();
        Note note = new Note();
        //WHEN
        when(noteRepository.findById(any())).thenReturn(noteOptional);

        //THEN
        assertThatThrownBy(() -> {
            noteService.deleteNoteById("test");
        })
                .isInstanceOf(NoteErrorException.class)
                .hasMessageContaining("Not found note with this id");
    }

}
