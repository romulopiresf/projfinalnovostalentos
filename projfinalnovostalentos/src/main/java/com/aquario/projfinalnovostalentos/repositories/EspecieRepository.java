package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Especie;

public interface EspecieRepository extends CrudRepository<Especie,Integer> {

    Optional<Especie> findByNome(String nome);
    Optional<Especie> findByPk(long pk);
    Iterable<Especie> findByAquario(Aquario aquario);

    Iterable<Especie> findAllByOrderByPkDesc();
    
}
