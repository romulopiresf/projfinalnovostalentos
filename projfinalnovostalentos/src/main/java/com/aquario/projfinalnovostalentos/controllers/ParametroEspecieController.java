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
import com.aquario.projfinalnovostalentos.models.ParametroEspecie;
import com.aquario.projfinalnovostalentos.repositories.EspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroEspecieRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ParametroEspecieController extends GenericController {

    @Autowired
    private ParametroEspecieRepository repository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @GetMapping("/editar-parametro-especie/{aquario_pk}/{pk}")
    public String editar(@PathVariable("aquario_pk") long aquario_pk, @PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Optional<Especie> buscaEspecie = especieRepository.findByPk(aquario_pk);
        if(!buscaEspecie.isPresent())
            return "redirect:/";
        Especie especie = buscaEspecie.get();
        modelMap.addAttribute("especie", especie);

        System.out.println(pk);

        Optional<ParametroEspecie> busca = this.repository.findByPk(pk);
        ParametroEspecie parametroEspecie = null;
        if(busca.isPresent())
            parametroEspecie = busca.get();
        else
            parametroEspecie = new ParametroEspecie();    
        System.out.println(parametroEspecie);

        Iterable<Parametro> parametros = parametroRepository.findAllByOrderByPkDesc();
        modelMap.addAttribute("parametros", parametros);

        modelMap.addAttribute("parametroEspecie", parametroEspecie);

        setEditPage(false);
        this.setup(modelMap, "Editar Parâmetro da Espécie", null, "/ver-especie/" + especie.getPk());
        return "editar-parametro-especie";
    }

    @PostMapping("/salvar-parametro-especie")
    public String salvar(ParametroEspecie parametroEspecie, @RequestParam("parametro_pk") long parametro_pk, @RequestParam("especie_pk") long especie_pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(parametroEspecie);
        try{    
            
            Optional<Especie> buscaEspecie = especieRepository.findByPk(especie_pk);
            if(!buscaEspecie.isPresent())
                throw new Exception("Espécie inválida");
            Especie especie = buscaEspecie.get();
            
            Optional<Parametro> buscaParametro = parametroRepository.findByPk(parametro_pk);
            if(!buscaParametro.isPresent())
                throw new Exception("Parâmetro inválido");
                Parametro parametro = buscaParametro.get();
            
            parametroEspecie.setEspecie(especie);
            parametroEspecie.setParametro(parametro);
            parametroEspecie = this.repository.save(parametroEspecie);  
            return "redirect:/ver-especie/" + especie.getPk();
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect://editar-parametro-especie/" + especie_pk + "/" + parametroEspecie.getPk();
        }
    }

    
}
