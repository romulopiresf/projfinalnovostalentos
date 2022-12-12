package com.aquario.projfinalnovostalentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.Especie;
import com.aquario.projfinalnovostalentos.models.ParametroAquario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.EquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.EspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroAquarioRepository;
import com.aquario.projfinalnovostalentos.services.EquipamentoService;
import com.aquario.projfinalnovostalentos.services.ParametroService;
import com.aquario.projfinalnovostalentos.utils.FileUpload;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AquarioController extends GenericController {

    @Autowired
    private AquarioRepository repository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ParametroAquarioRepository parametroAquarioRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private ParametroService parametroService;

    @GetMapping("/aquarios")
    public String lista(ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        Iterable<Aquario> items = repository.findAllByOrderByPkDesc();
        modelMap.addAttribute("aquarios", items);
        System.out.println(items);

        setEditPage(false);
    
        this.setup(modelMap, "Aquários", "/editar-aquario/0", null, true);
        return "aquarios";
    }

    @GetMapping("/ver-aquario/{pk}")
    public String ver(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Aquario> busca = this.repository.findByPk(pk);
        if(!busca.isPresent())
            return "redirect:/aquarios";

        Aquario aquario = busca.get();
        System.out.println(aquario);
        modelMap.addAttribute("aquario", aquario);

        Iterable<Equipamento> equipamentos = equipamentoRepository.findByAquario(aquario);
        System.out.println(equipamentos);
        modelMap.addAttribute("equipamentos", equipamentos);

        Iterable<ParametroAquario> parametros = parametroAquarioRepository.findByAquario(aquario);
        for (ParametroAquario parametroAquario : parametros) {
            parametroService.calcularClasse(parametroAquario);
        }
        System.out.println(parametros);
        modelMap.addAttribute("parametros", parametros);

        Iterable<Especie> especies = especieRepository.findByAquario(aquario);
        System.out.println(especies);
        modelMap.addAttribute("especies", especies);

        setEditPage(true);
        this.setup(modelMap, "Aquário", "/editar-aquario/" + aquario.getPk(), null, true);
        return "aquario";
    }

    @GetMapping("/editar-aquario/{pk}")
    public String editar(@PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
        System.out.println(pk);
        Optional<Aquario> busca = this.repository.findByPk(pk);
        Aquario aquario = null;
        if(busca.isPresent())
            aquario = busca.get();
        else
            aquario = new Aquario();    
        System.out.println(aquario);
        modelMap.addAttribute("aquario", aquario);
        this.setup(modelMap, "Editar Aquário", null, "/aquarios");
        return "editar-aquario";
    }

    @PostMapping("/salvar-aquario")
    public String salvar(Aquario aquario, @RequestParam("imagem") MultipartFile file, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";
    
        System.out.println(aquario);
        try{    

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if(filename != null && ! filename.isEmpty()){
                if(aquario.getPk() == 0)
                {
                    aquario = repository.save(aquario);
                }
                filename = "aquario_"+ aquario.getPk() + ".png";
                System.out.println(filename);
                FileUpload.save(null, filename, file);
                aquario.setFoto(filename);
                System.out.println(aquario);
            }

            aquario.addUsuario(getUsuario());
            aquario = this.repository.save(aquario);      
            updateUsuario();
            return "redirect:/aquarios";
        }
        catch(Exception ex){
            System.out.println(ex);
            modelMap.addAttribute("erro", ex.getMessage());
            return "redirect:/editar-aquario/" + aquario.getPk();
        }
    }

    @GetMapping("/mudar-equipamento/{aquario_pk}/{pk}")
    public String mudarEquipamento(@PathVariable("aquario_pk") int aquario_pk, @PathVariable("pk") int pk, ModelMap modelMap){
        if(!isLogged())
            return "redirect:/login";

        System.out.println(aquario_pk);
        Optional<Aquario> busca = this.repository.findByPk(aquario_pk);
        if(!busca.isPresent())
            return "redirect:/aquarios";

        Aquario aquario = busca.get();   
        System.out.println(aquario);

        System.out.println(pk);
        equipamentoService.mudarStatus(pk);
        
        return "redirect:/ver-aquario/" + aquario.getPk();
    }

    
}
