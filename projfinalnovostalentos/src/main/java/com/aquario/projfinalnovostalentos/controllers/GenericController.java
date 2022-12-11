package com.aquario.projfinalnovostalentos.controllers;

import com.aquario.projfinalnovostalentos.ProjfinalnovostalentosApplication;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

public class GenericController {

    private static Usuario usuario;
    private boolean editPage = false;

    @Autowired
    private UsuarioRepository usuario_repository;


    protected Usuario getUsuario() {
        if(!isLogged())
            return null;
        Optional<Usuario> usuario = usuario_repository.findByPk(GenericController.usuario.getPk());
        if(usuario.isPresent()){
            return usuario.get();
        }
        return null;
    }

    protected void updateUsuario(){
        if(isLogged()){
            usuario_repository.save(getUsuario());
        }
    }

    protected void setUsuario(Usuario usuario) {
        GenericController.usuario = usuario;
    }

    protected boolean isLogged(){
        return GenericController.usuario != null;
    }

    protected void setEditPage(boolean editPage){
        this.editPage = editPage;
    }

    protected void setup (ModelMap modelMap, String pageName){
        setup(modelMap, pageName, null, null, false);
    }

    protected void setup (ModelMap modelMap, String pageName, String addPage){
        setup(modelMap, pageName, addPage, null, false);
    }

    protected void setup (ModelMap modelMap, String pageName, String addPage, String cancelPage){
        setup(modelMap, pageName, addPage, cancelPage, false);
    }

    protected void setup (ModelMap modelMap, String pageName, String addPage, String cancelPage, boolean btnHome){
        modelMap.addAttribute("appName", ProjfinalnovostalentosApplication.AppName);
        modelMap.addAttribute("title", ProjfinalnovostalentosApplication.AppName + " - " + pageName);
        modelMap.addAttribute("page", pageName);
        if(addPage!=null){
            if(this.editPage)
                modelMap.addAttribute("editPage", addPage);
            else
                modelMap.addAttribute("addPage", addPage);
        }
        if(cancelPage != null)
        {
            modelMap.addAttribute("cancelPage", cancelPage);
        }
        if(btnHome)
        {
            modelMap.addAttribute("homePage", "/");
        }
        if(isLogged()){
            modelMap.addAttribute("usuario", getUsuario()); 
        
        }
    }

    
}
