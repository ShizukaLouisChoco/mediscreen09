package com.example.services.note.service;


import com.example.services.note.entity.Note;
import com.example.services.note.exception.NoteErrorException;
import com.example.services.note.repository.NoteRepository;
import com.example.services.note.service.impl.NoteServiceImpl;
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
        final Note registeredNote = new Note(1L,1L,"Note1", LocalDate.of(1966,12,31));

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
        final Note registeredNote = new Note(1L,1L,"Note1", LocalDate.of(1966,12,31));

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



}
