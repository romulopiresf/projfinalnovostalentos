package com.aquario.projfinalnovostalentos.services;

import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.HistoricoEquipamento;
import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.EquipamentoRepository;
import com.aquario.projfinalnovostalentos.repositories.HistoricoEquipamentoRepository;
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
public class EquipamentoService {
    private EquipamentoRepository repository;

    @Autowired
    private HistoricoEquipamentoRepository historicoEquipamentoRepository;
    
    public EquipamentoService(EquipamentoRepository repository){
        this.repository = repository;
    }

    public void mudarStatus(long pk)
    {
        Optional<Equipamento> busca = this.repository.findByPk(pk);
        if(!busca.isPresent())
            return;

        Equipamento equipamento = busca.get(); 
        mudarStatus(pk, !equipamento.isStatus());
    }

    public void mudarStatus(long pk, boolean status)
    {
        Optional<Equipamento> busca = this.repository.findByPk(pk);
        if(!busca.isPresent())
            return;

        Equipamento equipamento = busca.get(); 
        equipamento.setStatus(status);
        equipamento = this.repository.save(equipamento); 

        long millis = System.currentTimeMillis();

        HistoricoEquipamento historico = new HistoricoEquipamento();
        historico.setStatus(status);
        historico.setData(new Date(millis));
        historico.setHora(new Time(millis));
        historico.setEquipamento(equipamento);

        historicoEquipamentoRepository.save(historico);
    }

    public Iterable<HistoricoEquipamento> getHistorico(){
        return getHistorico(null);
    }

    public Iterable<HistoricoEquipamento> getHistorico(Equipamento equipamento){
        if(equipamento == null)
            return historicoEquipamentoRepository.findAll();
        else
            return historicoEquipamentoRepository.findByEquipamento(equipamento);
    }

}
