package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;

import org.springframework.ui.ModelMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends GenericController {

    @Autowired
    private AquarioRepository repository;

    @GetMapping("/home")
    public String homePage(ModelMap modelMap){
        Iterable<Aquario> aquarios = repository.findAll();
        modelMap.addAttribute("aquarios", aquarios);
    
        this.setup(modelMap, "Home");
        return "home";
    }
    
    
}
