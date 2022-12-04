package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.UsuarioRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
public class UtilsController extends GenericController {

    
    @GetMapping(value= "/imagem/**", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] imagem(HttpServletRequest request){
        String uri = request.getRequestURI();
        String path = uri.replace("/imagem/", "");
        System.out.println(path);
        return FileUpload.readFile(path);  
    }
    
}
