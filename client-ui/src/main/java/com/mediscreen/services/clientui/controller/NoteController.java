package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.beans.NoteBean;
import com.mediscreen.services.clientui.proxy.NoteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    //Logger log = LoggerFactory.getLogger(this.getClass());

    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    @GetMapping("/notes")
    public String getAllNote( Model model) {
        //log.info("getmapping /notes returns notePage.html");
        model.addAttribute("notes", noteProxy.getAllNote());

        return "notePage";
    }

    @GetMapping("/notes/{id}")
    public String getNote(@PathVariable("id") String id, Model model) {
        //log.info("getmapping /notes returns notePage.html");
        model.addAttribute("note", noteProxy.getNote(id));

        return "notePage";
    }
    @GetMapping("/notes/patients/{patientId}")
    public String getNoteByPatientId(@PathVariable Long patientId, Model model) {
        //log.info("getmapping /notes/patients/" + patientId + "returns notePage.html");

        model.addAttribute("notes", noteProxy.getNoteByPatientId(patientId));

        return "notePage";
    }

    @GetMapping("/notes/add")
    public String addNotePage(Model model) {
        //log.info("getmapping /notes/add/ returns noteAdd.html");

        model.addAttribute("note", new NoteBean());

        return "noteAdd";
    }

    @PostMapping("/notes")
    public String addNote( NoteBean note) {
        //log.info("postmapping /notes/add/"+patientId+" redirect to notes/patients/"+patientId);
        note.setId("");
        noteProxy.addNote(note);

        return "redirect:/notes/patients/" + note.getPatientId();
    }

    @GetMapping("/notes/update/{id}")
    public String updateNotePage(@PathVariable String id, Model model) {
        //log.info("postmapping /notes/update/"+id+" returns noteUpdate.html");

        model.addAttribute("note", noteProxy.getNote(id));

        return "noteUpdate";
    }

    @PutMapping("/notes")
    public String updateNote(NoteBean note) {
        //log.info("postmapping /notes/"+id+" redirects to notes/patients/" + id);

        noteProxy.updateNote(note);

        return "redirect:/notes/" + note.getId();
    }


    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        Long patientId = noteProxy.getNote(id).getPatientId();
        //log.info("getmapping /notes/delete/"+id+" redirects to notes/patients/" + patientId);

        noteProxy.deleteNoteById(id);

        return "redirect:/notes" ;
    }


    @DeleteMapping("/notes/patients/{patientId}")
    public String deletePatientNotes(@PathVariable Long patientId) {

        //log.info("deletemapping /notes/patients/"+ patientId+" redirects to notes/patients/" + patientId);

        noteProxy.deleteNoteByPatientId(patientId);

        return "redirect:/patients/" + patientId;
    }
}
