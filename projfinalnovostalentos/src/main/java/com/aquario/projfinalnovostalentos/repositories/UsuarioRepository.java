package com.aquario.projfinalnovostalentos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aquario.projfinalnovostalentos.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByPk(long pk);

    Iterable<Usuario> findAllByOrderByPkDesc();
}
