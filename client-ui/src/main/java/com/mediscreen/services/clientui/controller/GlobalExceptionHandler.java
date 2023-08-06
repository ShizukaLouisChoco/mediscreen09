package com.mediscreen.services.clientui.controller;

import com.mediscreen.services.clientui.exception.NoteErrorException;
import com.mediscreen.services.clientui.exception.PatientErrorException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PatientErrorException.class)
    public String handleNoResourceException(PatientErrorException ex, Model model) {
        //log.info("exception handling because of no resource exception");
        model.addAttribute("errorMsg", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(NoteErrorException.class)
    public String handleResourceAlreadyExistException(NoteErrorException ex, Model model) {
        //log.info("exception handling because of resource already exists exception");
        model.addAttribute("errorMsg", ex.getMessage());
        return "error";
    }


}