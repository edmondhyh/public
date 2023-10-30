package com.edmond.securtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @PostMapping("/register")
    public String registerPage(){
        return "register";
    }
}
