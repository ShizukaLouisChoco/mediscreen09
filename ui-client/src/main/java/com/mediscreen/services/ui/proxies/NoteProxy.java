package com.mediscreen.services.ui.proxies;

import com.mediscreen.services.ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
//@LoadBalancerClient(name = "note-service")
public interface NoteProxy {
    //CREATE
    @PostMapping(value = "/notes")
    NoteBean addNote(@RequestBody NoteBean noteBean);

    //READ
    @GetMapping("/notes/{id}")
    NoteBean getNote(@PathVariable String id);

    //READ BY PATIENT ID
    @GetMapping("/notes/patients/{id}")
    List<NoteBean> getNoteByPatientId(@PathVariable Long id);

    //READ ALL
    @GetMapping("/notes")
    NoteBean getAllNote();

    //UPDATE
    @PutMapping("/notes/{id}")
    NoteBean updateNote (@PathVariable String id, @RequestBody NoteBean noteBean);

    //DELETE
    @DeleteMapping("/notes/{id}")
    void deleteNoteById(@PathVariable String id);

    @DeleteMapping("/notes/patients/{id}")
    void deleteNoteByPatientId(@PathVariable Long id);
}
