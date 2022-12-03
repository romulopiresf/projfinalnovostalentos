package com.aquario.projfinalnovostalentos.controllers;

import com.aquario.projfinalnovostalentos.ProjfinalnovostalentosApplication;
import com.aquario.projfinalnovostalentos.models.Usuario;

import org.springframework.ui.ModelMap;

public class GenericController {

    private static Usuario usuario;


    protected Usuario getUsuario() {
        return GenericController.usuario;
    }

    protected void setUsuario(Usuario usuario) {
        GenericController.usuario = usuario;
    }

    protected boolean isLogged(){
        return getUsuario() != null;
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
        if(isLogged()){
            modelMap.addAttribute("usuario", getUsuario()); 
        
        }
    }

    
}
