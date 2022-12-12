package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Especie;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.ParametroEspecie;

public interface ParametroEspecieRepository extends CrudRepository<ParametroEspecie,Integer> {

    Optional<ParametroEspecie> findByPk(long pk);
    Iterable<ParametroEspecie> findByEspecie(Especie especie);
    Iterable<ParametroEspecie> findByParametro(Parametro parametro);

    Iterable<ParametroEspecie> findAllByOrderByPkDesc();

    @Query("SELECT DISTINCT p FROM ParametroEspecie p INNER JOIN Especie e ON p.especie = e WHERE e.aquario = :#{#aquario}")
    Iterable<ParametroEspecie> findByAquario(@Param("aquario") Aquario aquario);

    @Query("SELECT DISTINCT p FROM ParametroEspecie p INNER JOIN Especie e ON p.especie = e WHERE e.aquario = :#{#aquario} AND p.parametro = :#{#parametro}")
    Iterable<ParametroEspecie> findByAquarioAndParametro(@Param("aquario") Aquario aquario, @Param("parametro") Parametro parametro);
}
