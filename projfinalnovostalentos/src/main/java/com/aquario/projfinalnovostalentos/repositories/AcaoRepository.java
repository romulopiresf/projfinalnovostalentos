package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aquario.projfinalnovostalentos.models.Acao;
import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Equipamento;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.Usuario;

public interface AcaoRepository extends CrudRepository<Acao,Integer> {

    Optional<Acao> findByPk(long pk);
    Iterable<Acao> findByAquario(Aquario aquario);
    Iterable<Acao> findByParametro(Parametro parametro);
    Iterable<Acao> findByEquipamento(Equipamento equipamento);

    Iterable<Acao> findAllByOrderByPkDesc();
    
    
}
