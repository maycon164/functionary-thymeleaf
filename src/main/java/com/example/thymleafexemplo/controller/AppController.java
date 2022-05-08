package com.example.thymleafexemplo.controller;

import com.example.thymleafexemplo.dao.FunctionaryDao;
import com.example.thymleafexemplo.model.Functionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
public class AppController {

    @Autowired
    private FunctionaryDao repository;

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("something", "HELLO, WORLD!!!");
        return "index";
    }

    @GetMapping("/functionaries")
    public String getAllFunctionaries(Model model){
        List<Functionary> functionaries = repository.findAll();
        model.addAttribute("functionaries", functionaries);
        return "index";
    }

    @GetMapping("/functionaries/update/{id}")
    public String selectToUpdate(Model model, @PathVariable("id") Long id){

        Functionary func = repository.findById(id);
        model.addAttribute("updateFunctionary", func);
        return "index";
    }

    @GetMapping("/functionaries/delete/{id}")
    public String deleteFunctionary(Model model, @PathVariable("id") Long id){
        boolean result = repository.deleteById(id);
        if(result){

            model.addAttribute("message", "Functionary with id: " + id +" was deleted");
            return "index";

        }else{
            model.addAttribute("erro", "Could not delete functionary with id: " + id);
            return "erro";
        }
    }

    @PostMapping("/insert")
    public String insertFunctionary(Model model, Functionary func){
        Functionary savedFunctionary = repository.insert(func);
        System.out.println(savedFunctionary);
        model.addAttribute("savedFunctionary", savedFunctionary);
        return "index";
    }

    @PostMapping("/functionaries/{id}")
    public String updateFunctionary(Model model, Functionary func, @PathVariable("id") Long id){
        boolean result = repository.update(id, func);
        if(result){
            model.addAttribute("message", "functionary with id " + id + " was updated");
            return "index";
        }else{
            model.addAttribute("erro", "could not update functionary with id: " +id );
            return "error";
        }
    }


}
