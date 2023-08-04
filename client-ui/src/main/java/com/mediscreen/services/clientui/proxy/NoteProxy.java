package com.mediscreen.services.clientui.proxy;

import com.mediscreen.services.clientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
//@LoadBalancerClient(name = "note-service")
public interface NoteProxy {

    //READ
    @GetMapping("/notes/{id}")
    NoteBean getNote(@PathVariable String id);

    //READ BY PATIENT ID
    @GetMapping("/notes/patients/{patientId}")
    List<NoteBean> getNoteByPatientId(@PathVariable("patientId") Long patientId);

    //READ ALL
    @GetMapping("/notes/all")
    List<NoteBean> getAllNote();

    //UPDATE
    @PutMapping("/notes")
    NoteBean updateNote (@RequestBody NoteBean noteBean);

    @GetMapping("/notes/update/{id}")
    NoteBean updateNotePage (@PathVariable String  id);

    //DELETE
    @DeleteMapping("/notes/{id}")
    void deleteNoteById(@PathVariable String id);

    @DeleteMapping("/notes/patients/{patientId}")
    void deleteNoteByPatientId(@PathVariable Long patientId);

    //CREATE
    @PostMapping(value = "/notes")
    NoteBean addNote(@RequestBody NoteBean note);

    @GetMapping(value ="/notes/add")
    NoteBean addNoteForm();
}
