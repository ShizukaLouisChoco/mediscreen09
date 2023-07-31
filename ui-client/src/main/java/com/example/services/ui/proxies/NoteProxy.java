package com.example.services.ui.proxies;

import com.example.services.ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service", url="localhost:9100")
public interface NoteProxy {
    //CREATE
    @PostMapping(value = "/notes")
    NoteBean addNote(@RequestBody NoteBean noteBean);

    //READ
    @GetMapping("/notes/{id}")
    NoteBean getNote(@PathVariable Long id);

    //READ BY PATIENT ID
    @GetMapping("/notes/patients/{id}")
    List<NoteBean> getNoteByPatientId(@PathVariable Long id);

    //READ ALL
    @GetMapping("/notes")
    NoteBean getAllNote();

    //UPDATE
    @PutMapping("/notes/{id}")
    NoteBean updateNote (@PathVariable Long id, @RequestBody NoteBean noteBean);

    //DELETE
    @DeleteMapping("/notes/{id}")
    void deleteNoteById(@PathVariable Long id);

    @DeleteMapping("/notes/patients/{id}")
    void deleteNoteByPatientId(@PathVariable Long id);
}
