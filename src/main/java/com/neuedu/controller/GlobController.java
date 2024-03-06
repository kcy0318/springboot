package com.neuedu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller("/error")
public class GlobController {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        ResponseEntity<String> body = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        System.out.println(body.getStatusCode());
        model.addAttribute("code", body.getStatusCodeValue());
        model.addAttribute("msg", body.getStatusCode());
        model.addAttribute("msg1",body.getBody());
        return "error/4xx";
    }
}
