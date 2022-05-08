package com.example.thymleafexemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("something", "HELLO, WORLD!!!");
        return "index";
    }
}
