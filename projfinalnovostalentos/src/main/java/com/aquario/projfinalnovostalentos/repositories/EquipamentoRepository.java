package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;

public interface EquipamentoRepository extends CrudRepository<Equipamento,Integer> {

    Optional<Equipamento> findByNome(String nome);
    Optional<Equipamento> findByPk(long pk);
    Iterable<Equipamento> findByAquario(Aquario aquario);

    Iterable<Equipamento> findAllByOrderByPkDesc();
}
