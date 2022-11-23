package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.aquario.projfinalnovostalentos.ProjfinalnovostalentosApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(ModelMap modelMap){
        modelMap.addAttribute("appName", ProjfinalnovostalentosApplication.AppName);
        modelMap.addAttribute("title", ProjfinalnovostalentosApplication.AppName + " - Login");
        return "login";
    }

    
}
