package com.aquario.projfinalnovostalentos.controllers;

import com.aquario.projfinalnovostalentos.ProjfinalnovostalentosApplication;
import com.aquario.projfinalnovostalentos.models.Usuario;

import org.springframework.ui.ModelMap;

public class GenericController {

    private Usuario usuario;


    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    protected void setup (ModelMap modelMap, String pageName){
        modelMap.addAttribute("appName", ProjfinalnovostalentosApplication.AppName);
        modelMap.addAttribute("title", ProjfinalnovostalentosApplication.AppName + " - " + pageName);
        if(usuario!=null){
            modelMap.addAttribute("userName", usuario.getNome()); 
        }
    }

    
}
