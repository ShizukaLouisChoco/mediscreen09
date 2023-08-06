package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.beans.NoteBean;
import com.mediscreen.services.clientui.exception.NoteErrorException;
import com.mediscreen.services.clientui.proxy.NoteProxy;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class NoteController {

    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    //CREATE
    @PostMapping("/notes")
    public String addNote(@Valid NoteBean note, BindingResult result, Model model) throws Exception {
        log.info("postmapping /notes for addNote()");
        model.addAttribute("note", note);
        if(result.hasErrors()) {
            log.info("validation error !");
            List<String> results = new ArrayList<>(Arrays.asList(result.toString().split("\\s*,\\s*")));
            model.addAttribute("result",results);
            return "noteAdd";
        }try{
            log.info("creating new note");
            note.setId("");
            noteProxy.addNote(note);
            model.addAttribute("notes",noteProxy.getNoteByPatientId(note.getPatientId()));
        }catch(Exception exception){
            model.addAttribute("note",note);
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg",exception.getMessage());
            return "noteAdd";
        }
        return "redirect:/notes";
    }

    //CREATE FORM
    @GetMapping("/notes/add")
    public String addNoteForm(Model model) {
        log.info("getmapping /notes/add returns noteAdd.html");

        model.addAttribute("note", new NoteBean());

        return "noteAdd";
    }

    //READ ALL
    @GetMapping("/notes")
    public String getAllNote( Model model) {
        log.info("getmapping /notes for getAllNote()");
        model.addAttribute("notes", noteProxy.getAllNote());

        return "notePage";
    }

    //READ ONE
    @GetMapping("/notes/{id}")
    public String getNote(@PathVariable("id") String id, Model model) {
        log.info("getmapping /notes returns for getNote()");
        model.addAttribute("note", noteProxy.getNote(id));

        return "notePage";
    }

    //READ ALL OF ONE
    @GetMapping("/notes/patients/{patientId}")
    public String getNoteByPatientId(@PathVariable Long patientId, Model model) {
        log.info("getmapping /notes/patients/" + patientId + " for  getnoteByPatientId()");

        model.addAttribute("notes", noteProxy.getNoteByPatientId(patientId));

        return "notePage";
    }



    //UPDATE FORM
    @GetMapping("/notes/update/{id}")
    public String updateNoteForm(@PathVariable String id, Model model) {
        log.info("getmapping /notes/update/{id} for updateNoteForm()");

        model.addAttribute("note", noteProxy.getNote(id));

        return "noteUpdate";
    }

    //UPDATE
    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable String id, @Valid NoteBean note, BindingResult result, Model model) throws NoteErrorException{
        log.info("putmapping /notes/update for updateNote()");
        model.addAttribute("note",note);
        if(result.hasErrors()){
            log.info("validation error");
            model.addAttribute("note",note);
            return "noteUpdate";
        }try{
            log.info("updating note");
            noteProxy.updateNote(id, note);
        }catch (Exception exception){
            model.addAttribute("note",note);
            log.error(String.valueOf(exception));
            model.addAttribute("errorMsg",exception.getMessage());
            return "updateNotePage";
        }
        log.info("note is updated");
        return "redirect:/notes/" + note.getId();
    }


    //DELETE ONE
    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        Long patientId = noteProxy.getNote(id).getPatientId();
        log.info("getmapping /notes/delete/{id} for deleteNote()");

        noteProxy.deleteNoteById(id);

        return "redirect:/notes" ;
    }


    //DELETE ALL OF ONE
    @GetMapping("/notes/patients/delete/{patientId}")
    public String deletePatientNotes(@PathVariable Long patientId) {
        log.info("deletemapping /notes/patients/{patientId} for deletePatientNotes()");

        noteProxy.deleteNoteByPatientId(patientId);

        return "redirect:/notes";
    }
}
