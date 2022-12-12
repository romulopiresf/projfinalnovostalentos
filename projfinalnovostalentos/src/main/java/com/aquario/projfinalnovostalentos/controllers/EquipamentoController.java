package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.HistoricoEquipamento;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.EquipamentoRepository;
import com.aquario.projfinalnovostalentos.services.EquipamentoService;

import org.springframework.ui.ModelMap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EquipamentoController extends GenericController {

    @Autowired
    private EquipamentoRepository repository;

    @Autowired
    private AquarioRepository aquarioRepository;

    @Autowired
    private EquipamentoService service;

    @GetMapping("/equipamentos")
    public String lista(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<Equipamento> items = repository.findAllByOrderByPkDesc();
        modelMap.addAttribute("equipamentos", items);
        System.out.println(items);
    
        this.setup(modelMap, "Equipamentos", "/editar-equipamento/0", null, true);
        return "equipamentos";
    }

    @GetMapping("/editar-equipamento/{pk}")
    public String editar(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Equipamento> busca = this.repository.findByPk(pk);
        Equipamento equipamento = null;
        if(busca.isPresent())
            equipamento = busca.get();
        else
            equipamento = new Equipamento();    
        System.out.println(equipamento);

        Iterable<Aquario> aquarios = aquarioRepository.findAllByOrderByPkDesc();
        modelMap.addAttribute("aquarios", aquarios);

        modelMap.addAttribute("equipamento", equipamento);
        this.setup(modelMap, "Editar Equipamento", null, "/equipamentos");
        return "editar-equipamento";
    }

    @PostMapping("/salvar-equipamento")
    public String salvar(Equipamento equipamento, @RequestParam("aquario_pk") long aquario_pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(equipamento);
        
        try{  
            Optional<Aquario> busca = aquarioRepository.findByPk(aquario_pk);
            if(!busca.isPresent())
                throw new Exception("Aquario inválido");
            
            equipamento.setAquario(busca.get());
            System.out.println(equipamento);

            equipamento = this.repository.save(equipamento);      
            return "redirect:/equipamentos";
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect:/editar-equipamento/" + equipamento.getPk();
        }
    }

    @GetMapping("/mudar-status-equipamento/{pk}")
    public String status(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        service.mudarStatus(pk);
        return "redirect:/equipamentos";
    }

    @GetMapping("/historico")
    public String historico(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<HistoricoEquipamento> items = service.getHistorico();
        modelMap.addAttribute("historico", items);
        System.out.println(items);
    
        this.setup(modelMap, "Histórico de Equipamentos", null, null, true);
        return "historico";
    }
}
