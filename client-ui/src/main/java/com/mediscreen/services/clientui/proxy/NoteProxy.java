package com.mediscreen.services.clientui.proxy;

import com.mediscreen.services.clientui.beans.NoteBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service", url = "localhost:9100")
@LoadBalancerClient(name = "note-service")
public interface NoteProxy {

    //CREATE
    @PostMapping(value = "/notes")
    NoteBean addNote(@RequestBody NoteBean note);

    //READ ALL
    @GetMapping("/notes")
    List<NoteBean> getAllNote();

    //READ
    @GetMapping("/notes/{id}")
    NoteBean getNote(@PathVariable String id);

    //READ BY PATIENT ID
    @GetMapping("/notes/patients/{patientId}")
    List<NoteBean> getNoteByPatientId(@PathVariable("patientId") Long patientId);


    //UPDATE
    @PutMapping("/notes/{id}")
    NoteBean updateNote (@PathVariable String id, @RequestBody NoteBean noteBean);

    //DELETE
    @GetMapping("/notes/delete/{id}")
    void deleteNoteById(@PathVariable String id);

    @GetMapping("/notes/patients/delete/{patientId}")
    void deleteNoteByPatientId(@PathVariable Long patientId);


}
