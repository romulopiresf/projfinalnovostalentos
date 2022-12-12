package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.repositories.ParametroRepository;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParametroController extends GenericController {

    @Autowired
    private ParametroRepository repository;

    @GetMapping("/parametros")
    public String lista(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<Parametro> items = repository.findAllByOrderByPkDesc();
        modelMap.addAttribute("parametros", items);
        System.out.println(items);
    
        setEditPage(false);
        this.setup(modelMap, "Parâmetros", "/editar-parametro/0", null, true);
        return "parametros";
    }

    @GetMapping("/editar-parametro/{pk}")
    public String editar(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Parametro> busca = this.repository.findByPk(pk);
        Parametro parametro = null;
        if(busca.isPresent())
            parametro = busca.get();
        else
            parametro = new Parametro();    
        System.out.println(parametro);
        modelMap.addAttribute("parametro", parametro);
        this.setup(modelMap, "Editar Parâmetro", null, "/parametros");
        return "editar-parametro";
    }

    @PostMapping("/salvar-parametro")
    public String salvar(Parametro parametro, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(parametro);
        try{    
            parametro = this.repository.save(parametro);      
            return "redirect:/parametros";
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect:/editar-parametro/" + parametro.getPk();
        }
    }

    

    
}
