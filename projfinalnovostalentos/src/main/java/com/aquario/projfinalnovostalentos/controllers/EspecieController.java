package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Especie;
import com.aquario.projfinalnovostalentos.models.ParametroEspecie;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.EspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroEspecieRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EspecieController extends GenericController {

    @Autowired
    private EspecieRepository repository;

    @Autowired
    private AquarioRepository aquarioRepository;

    @Autowired
    private ParametroEspecieRepository parametroEspecieRepository;

    @GetMapping("/especies")
    public String lista(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<Especie> especies = repository.findAllByOrderByPkDesc();
        modelMap.addAttribute("especies", especies);
        System.out.println(especies);

        setEditPage(false);
    
        this.setup(modelMap, "Espécies", "/editar-especie/0", null, true);
        return "especies";
    }

    @GetMapping("/ver-especie/{pk}")
    public String ver(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Especie> busca = this.repository.findByPk(pk);
        if(!busca.isPresent())
            return "redirect:/especies";

        Especie especie = busca.get();
        System.out.println(especie);
        modelMap.addAttribute("especie", especie);

        Iterable<ParametroEspecie> parametros = parametroEspecieRepository.findByEspecie(especie);
        System.out.println(parametros);
        modelMap.addAttribute("parametros", parametros);

        setEditPage(true);
        this.setup(modelMap, "Espécie", "/editar-especie/" + especie.getPk(), null, true);
        return "especie";
    }

    @GetMapping("/editar-especie/{pk}")
    public String editar(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Especie> busca = this.repository.findByPk(pk);
        Especie especie = null;
        if(busca.isPresent())
            especie = busca.get();
        else
            especie = new Especie();    
        System.out.println(especie);

        Iterable<Aquario> aquarios = aquarioRepository.findAllByOrderByPkDesc();
        modelMap.addAttribute("aquarios", aquarios);

        modelMap.addAttribute("especie", especie);
        this.setup(modelMap, "Editar Espécie", null, "/especies");
        return "editar-especie";
    }

    @PostMapping("/salvar-especie")
    public String salvar(Especie especie, @RequestParam("aquario_pk") long aquario_pk, @RequestParam("imagem") MultipartFile file, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(especie);
        try{    
            
            Optional<Aquario> busca = aquarioRepository.findByPk(aquario_pk);
            if(!busca.isPresent())
                throw new Exception("Aquario inválido");
        

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if(filename != null && ! filename.isEmpty()){
                if(especie.getPk() == 0)
                {
                    especie.setAquario(busca.get());
                    especie = repository.save(especie);
                }
                filename = "especie_"+ especie.getPk() + ".png";
                System.out.println(filename);
                FileUpload.save(null, filename, file);
                especie.setFoto(filename);
                System.out.println(especie);
            }
            
            especie.setAquario(busca.get());
            especie = this.repository.save(especie);  
            return "redirect:/especies";
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect:/editar-especie/" + especie.getPk();
        }
    }

    
}
