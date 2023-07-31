package com.example.services.ui.controller;

import com.example.services.ui.beans.NoteBean;
import com.example.services.ui.proxies.NoteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class NoteController {
    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    @PostMapping("/notes/add/{patientId}")
    public String addNote(@PathVariable Long patientId, String note) {

        NoteBean noteBean = new NoteBean();

        noteBean.setPatientId(patientId);
        noteBean.setNote(note);
        noteBean.setDate(LocalDate.now());


        noteProxy.addNote(noteBean);


        return "redirect:http://localhost:8888/notes/patients/" + patientId;
    }

    @GetMapping("/notes/update/{id}")
    public String updateNotePage(@PathVariable Long id, Model model) {

        model.addAttribute("note", noteProxy.getNote(id));

        return "/noteUpdate";
    }

    @PutMapping("/notes/{id}")
    public String updateNote(@PathVariable Long id, NoteBean note) {

        noteProxy.updateNote(id, note);

        return "redirect:http://localhost:8888/notes/patients/" + note.getPatientId();
    }


    @DeleteMapping("/notes/{id}")
    public String deleteNote(@PathVariable Long id) {

        Long idPatient = noteProxy.getNote(id).getPatientId();

        noteProxy.deleteNoteById(id);

        return "redirect:http://localhost:8888/notes/patients/" + idPatient ;
    }
}
