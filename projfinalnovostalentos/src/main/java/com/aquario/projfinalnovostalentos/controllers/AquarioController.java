package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.services.UsuarioService;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
public class AquarioController extends GenericController {

    @Autowired
    private AquarioRepository repository;

    @GetMapping("/aquarios")
    public String aquariosPage(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<Aquario> aquarios = repository.findAll();
        modelMap.addAttribute("aquarios", aquarios);
        System.out.println(aquarios);
    
        this.setup(modelMap, "Aquários", "/editar-aquario/0");
        return "aquarios";
    }

    @GetMapping("/editar-aquario/{pk}")
    public String editarAquarioPage(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Aquario> aquarioFind = this.repository.findByPk(pk);
        Aquario aquario = null;
        if(aquarioFind.isPresent())
            aquario = aquarioFind.get();
        else
            aquario = new Aquario();    
        System.out.println(aquario);
        modelMap.addAttribute("aquario", aquario);
        this.setup(modelMap, "Editar Aquário", null, "/aquarios");
        return "editar-aquario";
    }

    @PostMapping("/salvar-aquario")
    public String salvarAquarioPage(Aquario aquario, @RequestParam("imagem") MultipartFile file, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(aquario);
        try{    

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if(filename != null && ! filename.isEmpty()){
                if(aquario.getPk() == 0)
                {
                    aquario = repository.save(aquario);
                }
                filename = "aquario_"+ aquario.getPk() + ".png";
                System.out.println(filename);
                FileUpload.save(null, filename, file);
                aquario.setFoto(filename);
                System.out.println(aquario);
            }

            aquario.addUsuario(getUsuario());
            aquario = this.repository.save(aquario);      
            updateUsuario();
            return "redirect:aquarios";
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect:editar-aquario/" + aquario.getPk();
        }
    }

    
}
