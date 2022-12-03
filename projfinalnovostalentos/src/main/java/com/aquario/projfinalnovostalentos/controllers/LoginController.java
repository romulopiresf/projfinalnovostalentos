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
public class LoginController extends GenericController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login")
    public String loginPage(ModelMap modelMap){
        if(isLogged())
            return "redirect:home";

        this.setUsuario(null);
        this.setup(modelMap, "Login");
        return "login";
    }

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String proccessForm (LoginForm loginForm, ModelMap modelMap){
        if(isLogged())
            return "redirect:home";

        try{
            Usuario usuario = this.service.login(loginForm);
            this.setUsuario(usuario);
            return "redirect:home";
        }
        catch(Exception ex){
            this.setUsuario(null);
            modelMap.addAttribute("erro", ex.getMessage());
            this.setup(modelMap, "Login");
            return "login";
        }
     
    }

    @GetMapping("/logout")
    public String logoutPage(ModelMap modelMap){
        this.setUsuario(null);
        return "redirect:login";
    }
    
}
