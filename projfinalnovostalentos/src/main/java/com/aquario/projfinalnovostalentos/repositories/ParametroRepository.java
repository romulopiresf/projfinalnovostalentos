package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Parametro;

public interface ParametroRepository extends CrudRepository<Parametro,Integer> {

    Optional<Parametro> findByNome(String nome);
    Optional<Parametro> findByPk(long pk);
    
}
