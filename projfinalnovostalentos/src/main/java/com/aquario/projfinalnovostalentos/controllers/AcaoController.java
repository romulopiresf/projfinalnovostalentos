package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquario.projfinalnovostalentos.models.Acao;
import com.aquario.projfinalnovostalentos.repositories.AcaoRepository;
import com.aquario.projfinalnovostalentos.services.AcaoService;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AcaoController extends GenericController {

    @Autowired
    private AcaoRepository repository;

    @Autowired
    private AcaoService service;

    @GetMapping("/acoes")
    public String lista(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        service.gerarAcoes();

        Iterable<Acao> items = repository.findAllByOrderByPkDesc();
        modelMap.addAttribute("acoes", items);
        System.out.println(items);
    
        this.setup(modelMap, "Ações", null, null, true);
        return "acoes";
    }
}
