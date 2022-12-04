package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.services.UsuarioService;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
public class CadastroUsuarioController extends GenericController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/cadastrar-usuario")
    public String registerPage(ModelMap modelMap){
        if(isLogged())
            return "redirect:home";

        this.setUsuario(null);
        this.setup(modelMap, "Cadastrar Usuário");
        return "cadastrar-usuario";
    }

    @PostMapping(value="/cadastrar-usuario", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String proccessForm (Usuario usuarioForm, ModelMap modelMap){
        if(isLogged())
            return "redirect:home";

        try{
            Usuario usuario = this.service.register(usuarioForm);
            this.setUsuario(usuario);
            return "redirect:home";
        }
        catch(Exception ex){
            this.setUsuario(null);
            modelMap.addAttribute("erro", ex.getMessage());
            this.setup(modelMap, "Cadastrar Usuário");
            return "cadastrar-usuario";
        }
     
    }

    @GetMapping("/editar-usuario")
    public String editarUsuarioPage(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        this.setup(modelMap, "Editar Perfil");
        return "editar-usuario";
    }

    @PostMapping("/salvar-usuario")
    public String salvarUsuarioPage(Usuario usuario, @RequestParam("imagem") MultipartFile file, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        
        try{
            Usuario usuarioAtualizado = this.service.update(usuario, file);
            this.setUsuario(usuarioAtualizado);
            return "redirect:editar-usuario";
        }
        catch(Exception ex){
            modelMap.addAttribute("erro", ex.getMessage());
            this.setup(modelMap, "Editar Perfil");
            return "editar-usuario";
        }
    }

    
}
