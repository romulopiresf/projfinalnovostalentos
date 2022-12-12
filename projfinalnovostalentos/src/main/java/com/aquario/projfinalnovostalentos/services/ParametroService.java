package com.aquario.projfinalnovostalentos.services;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.HistoricoEquipamento;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.ParametroAquario;
import com.aquario.projfinalnovostalentos.models.ParametroEspecie;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.AquarioRepository;
import com.aquario.projfinalnovostalentos.repositories.EquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.EspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.HistoricoEquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroEspecieRepository;
import com.aquario.projfinalnovostalentos.repositories.ParametroRepository;
import com.aquario.projfinalnovostalentos.utils.FileUpload;
import com.aquario.projfinalnovostalentos.utils.LoginForm;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParametroService {

    @Autowired
    private ParametroEspecieRepository parametroEspecieRepository;
    
    public ParametroService(){
    }

    public void calcularClasse(ParametroAquario parametroAquario )
    {
        Parametro parametro = parametroAquario.getParametro();
        Aquario aquario = parametroAquario.getAquario();

        Iterable<ParametroEspecie> parametros = this.parametroEspecieRepository.findByAquarioAndParametro(aquario, parametro);
        float media = 0;
        int total = 0;
        for (ParametroEspecie parametroEspecie : parametros) {
            media = parametroEspecie.getValor();
            total += 1;
        }

        if(total > 0)
            media = media / total;

        System.out.println("Média: " + media);

        float range = parametro.getMaximo() - parametro.getMinimo();
        System.out.println("Range: " + range);

        float passo = range * 0.1f;
        System.out.println("Passo: " + passo);

        float valor = parametroAquario.getValor();
        System.out.println("Valor: " + valor);



        String classe = "muito-ruim";
        if(valor >= media - passo && valor <= media + passo)
        {
            classe = "otimo";
        }
        else if(valor >= media - (passo * 2) && valor <= media + (passo * 2))
        {
            classe = "bom";
        }
        else if(valor >= media - (passo * 3) && valor <= media + (passo * 3))
        {
            classe = "ruim";
        }
        else{
            classe = "muito-ruim";
        }
        System.out.println("Classe: " + classe);
        parametroAquario.setClasse(classe);
    }
}
