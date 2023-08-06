package com.mediscreen.services.clientui.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    @GetMapping("/error")
    public String error(Model model, HttpServletResponse response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        //log.info("heading to error page because of : " + httpStatus.toString());
        model.addAttribute("errorStatus", httpStatus);
        return "error";
    }

}
