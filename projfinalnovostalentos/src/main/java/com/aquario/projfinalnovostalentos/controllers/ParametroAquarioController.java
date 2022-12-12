package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Especie;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.ParametroAquario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroAquarioRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParametroAquarioController extends GenericController {

    @Autowired
    private ParametroAquarioRepository repository;

    @Autowired
    private AquarioRepository aquarioRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @GetMapping("/editar-parametro-aquario/{aquario_pk}/{pk}")
    public String editar(@PathVariable("aquario_pk") long aquario_pk, @PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Optional<Aquario> buscaAquario = aquarioRepository.findByPk(aquario_pk);
        if(!buscaAquario.isPresent())
            return "redirect:/";
        Aquario aquario = buscaAquario.get();
        modelMap.addAttribute("aquario", aquario);

        System.out.println(pk);

        Optional<ParametroAquario> busca = this.repository.findByPk(pk);
        ParametroAquario parametroAquario = null;
        if(busca.isPresent())
            parametroAquario = busca.get();
        else
            parametroAquario = new ParametroAquario();    
        System.out.println(parametroAquario);

        Iterable<Parametro> parametros = parametroRepository.findAllByOrderByPkDesc();
        modelMap.addAttribute("parametros", parametros);

        modelMap.addAttribute("parametroAquario", parametroAquario);
        setEditPage(false);
        this.setup(modelMap, "Editar Parâmetro do Aquário", null, "/ver-aquario/" + aquario.getPk());
        return "editar-parametro-aquario";
    }

    @PostMapping("/salvar-parametro-aquario")
    public String salvar(ParametroAquario parametroAquario, @RequestParam("parametro_pk") long parametro_pk, @RequestParam("aquario_pk") long aquario_pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(parametroAquario);
        try{    
            
            Optional<Aquario> buscaAquario = aquarioRepository.findByPk(aquario_pk);
            if(!buscaAquario.isPresent())
                throw new Exception("Aquario inválido");
            Aquario aquario = buscaAquario.get();
            
            Optional<Parametro> buscaParametro = parametroRepository.findByPk(parametro_pk);
            if(!buscaParametro.isPresent())
                throw new Exception("Parâmetro inválido");
                Parametro parametro = buscaParametro.get();
            
            parametroAquario.setAquario(aquario);
            parametroAquario.setParametro(parametro);
            parametroAquario = this.repository.save(parametroAquario);  
            return "redirect:/ver-aquario/" + aquario.getPk();
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect://editar-parametro-aquario/" + aquario_pk + "/" + parametroAquario.getPk();
        }
    }

    
}
