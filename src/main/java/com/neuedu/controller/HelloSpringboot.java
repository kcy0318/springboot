package com.neuedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloSpringboot {
    @RequestMapping("/hello")
    public String toHello(){
        return "index";
    }
}
