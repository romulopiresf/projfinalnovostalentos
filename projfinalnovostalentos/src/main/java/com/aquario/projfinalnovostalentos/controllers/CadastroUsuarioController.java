package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.services.UsuarioService;
import com.aquario.projfinalnovostalentos.utils.LoginForm;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
public class CadastroUsuarioController extends GenericController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/cadastrar-usuario")
    public String loginPage(ModelMap modelMap){
        this.setUsuario(null);
        this.setup(modelMap, "Cadastrar Usuário");
        return "cadastrar-usuario";
    }

    @PostMapping(value="/cadastrar-usuario", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String proccessForm (Usuario usuarioForm, ModelMap modelMap){
        System.out.println(usuarioForm);
        try{
            Usuario usuario = this.service.register(usuarioForm);
            this.setUsuario(usuario);
            this.setup(modelMap, "Home");
            return "home";
        }
        catch(Exception ex){
            this.setUsuario(null);
            modelMap.addAttribute("erro", ex.getMessage());
            this.setup(modelMap, "Cadastrar Usuário");
            return "cadastrar-usuario";
        }
     
    }

    
}
