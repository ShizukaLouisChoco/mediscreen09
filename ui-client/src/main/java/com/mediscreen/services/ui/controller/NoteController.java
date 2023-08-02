package com.mediscreen.services.ui.controller;

import com.mediscreen.services.ui.beans.NoteBean;
import com.mediscreen.services.ui.proxies.NoteProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class NoteController {
    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    @GetMapping("/notes")
    public String getAllNote( Model model) {
        log.info("getmapping /notes returns notePage.html");
        model.addAttribute("notes", noteProxy.getAllNote());

        return "/notePage";
    }
    @GetMapping("/notes/patients/{patientId}")
    public String getNoteByPatientId(@PathVariable Long patientId, Model model) {
        log.info("getmapping /notes/patients/" + patientId + "returns notePage.html");

        model.addAttribute("notes", noteProxy.getNoteByPatientId(patientId));

        return "/notePage";
    }

    @GetMapping("/notes/add")
    public String addNotePage(Model model) {
        log.info("getmapping /notes/add/ returns noteAdd.html");

        model.addAttribute("note", new NoteBean());

        return "/noteAdd";
    }

    @PostMapping("/notes/add/{patientId}")
    public String addNote(@PathVariable Long patientId, NoteBean note) {
        log.info("postmapping /notes/add/"+patientId+" redirect to notes/patients/"+patientId);

        NoteBean noteBean = new NoteBean();

        noteBean.setPatientId(patientId);
        noteBean.setNote(noteBean.getNote());
        noteBean.setDate(noteBean.getDate());

        noteProxy.addNote(noteBean);


        return "redirect:http://localhost:8080/notes/patients/" + patientId;
    }

    @GetMapping("/notes/update/{id}")
    public String updateNotePage(@PathVariable String id, Model model) {
        log.info("postmapping /notes/update/"+id+" returns noteUpdate.html");

        model.addAttribute("note", noteProxy.getNote(id));

        return "/noteUpdate";
    }

    @PostMapping("/notes/{id}")
    public String updateNote(@PathVariable String id, NoteBean note) {
        log.info("postmapping /notes/"+id+" redirects to notes/patients/" + id);

        noteProxy.updateNote(id, note);

        return "redirect:http://localhost:8080/notes/patients/" + id;
    }


    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        Long patientId = noteProxy.getNote(id).getPatientId();
        log.info("getmapping /notes/delete/"+id+" redirects to notes/patients/" + patientId);

        noteProxy.deleteNoteById(id);

        return "redirect:http://localhost:8080/notes/patients/" + patientId ;
    }
}
