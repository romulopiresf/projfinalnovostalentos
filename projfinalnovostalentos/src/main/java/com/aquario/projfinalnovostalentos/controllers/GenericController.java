package com.aquario.projfinalnovostalentos.controllers;

import com.aquario.projfinalnovostalentos.ProjfinalnovostalentosApplication;
import com.aquario.projfinalnovostalentos.models.Usuario;

import org.springframework.ui.ModelMap;

public class GenericController {

    private static Usuario usuario;


    public Usuario getUsuario() {
        return GenericController.usuario;
    }

    public void setUsuario(Usuario usuario) {
        GenericController.usuario = usuario;
    }

    protected void setup (ModelMap modelMap, String pageName){
        setup(modelMap, pageName, null);
    }

    protected void setup (ModelMap modelMap, String pageName, String addPage){
        modelMap.addAttribute("appName", ProjfinalnovostalentosApplication.AppName);
        modelMap.addAttribute("title", ProjfinalnovostalentosApplication.AppName + " - " + pageName);
        modelMap.addAttribute("page", pageName);
        if(addPage!=null){
            modelMap.addAttribute("addPage", addPage);
        }
        if(usuario!=null){
            modelMap.addAttribute("userName", usuario.getNome()); 
            modelMap.addAttribute("userPhoto", usuario.getFoto());
        }
    }

    
}
