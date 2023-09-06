package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.beans.NoteBean;
import com.mediscreen.services.clientui.exception.NoteErrorException;
import com.mediscreen.services.clientui.proxy.NoteProxy;
import com.mediscreen.services.clientui.proxy.PatientProxy;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class NoteController {

    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;

    public NoteController(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
    }

    //CREATE
    @PostMapping("/notes")
    public String addNote(@Valid @ModelAttribute("note") NoteBean note, BindingResult result, Model model) throws Exception {
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
        return "redirect:/patients/"+note.getPatientId();
    }

    //CREATE FORM
    @GetMapping("/notes/{id}/add")
    public String addNoteForm(@PathVariable("id") Long id,Model model) {
        log.info("getmapping /notes/add returns noteAdd.html");

        model.addAttribute("note", new NoteBean("",id,"",LocalDate.now()));
        model.addAttribute("patient",patientProxy.getPatient(id));

        return "noteAdd";
    }

    //UPDATE FORM
    @GetMapping("/notes/update/{id}")
    public String updateNoteForm(@PathVariable String id, Model model) {
        log.info("getmapping /notes/update/{id} for updateNoteForm()");

        model.addAttribute("note", noteProxy.getNote(id));
        Long patientId = noteProxy.getNote(id).getPatientId();
        model.addAttribute("patient",patientProxy.getPatient(patientId));
        return "noteUpdate";
    }

    //UPDATE
    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable String id, @Valid @ModelAttribute("note") NoteBean note, BindingResult result, Model model) throws NoteErrorException{
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
        return "redirect:/patients/"+note.getPatientId();
    }


    //DELETE ONE
    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        Long patientId = noteProxy.getNote(id).getPatientId();
        log.info("getmapping /notes/delete/{id} for deleteNote()");

        noteProxy.deleteNoteById(id);

        return "redirect:/patients/"+patientId ;
    }

}
