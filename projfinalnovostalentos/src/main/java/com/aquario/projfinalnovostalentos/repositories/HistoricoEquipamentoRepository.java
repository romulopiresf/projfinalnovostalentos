package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.HistoricoEquipamento;

public interface HistoricoEquipamentoRepository extends CrudRepository<HistoricoEquipamento,Integer> {

    Optional<HistoricoEquipamento> findByPk(long pk);
    Iterable<HistoricoEquipamento> findByEquipamento(Equipamento equipamento);
    Optional<HistoricoEquipamento> findFirstByEquipamentoOrderByPkDesc(Equipamento equipamento);
    
    Iterable<HistoricoEquipamento> findAllByOrderByPkDesc();
}
