package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Aquario;

public interface AquarioRepository extends CrudRepository<Aquario,Integer> {

    Optional<Aquario> findByNome(String nome);
    Optional<Aquario> findByPk(long pk);

    Iterable<Aquario> findAllByOrderByPkDesc();
    
}
