package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.UsuarioRepository;

import org.springframework.ui.ModelMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends GenericController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AquarioRepository repository;

    @GetMapping("/home")
    public String homePage(ModelMap modelMap){
        if(!isLogged())
            return "redirect:login";

        Iterable<Aquario> aquarios = repository.findAll();
        modelMap.addAttribute("aquarios", aquarios);
    
        this.setup(modelMap, "Aquários", "/criar-aquario");
        return "home";
    }
    
    @GetMapping("/")
    public String indexPage(ModelMap modelMap){
        if(!isLogged()){
            return "redirect:login";
        }
        else{
            return "redirect:home";
        }
    }

    @GetMapping("/usuario-foto/{pk}")
    public @ResponseBody byte[] usuarioFoto(@PathVariable("pk") int pk){
        if(!isLogged()){
            return null;
        }
        Usuario usuario = usuarioRepository.findByPk(pk);
    }
    
}
