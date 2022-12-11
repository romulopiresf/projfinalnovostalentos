package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Aquario;
import com.aquario.projfinalnovostalentos.models.Parametro;
import com.aquario.projfinalnovostalentos.models.ParametroAquario;

public interface ParametroAquarioRepository extends CrudRepository<ParametroAquario,Integer> {

    Optional<ParametroAquario> findByPk(long pk);
    Iterable<ParametroAquario> findByAquario(Aquario aquario);
    Iterable<ParametroAquario> findByParametro(Parametro parametro);
}
