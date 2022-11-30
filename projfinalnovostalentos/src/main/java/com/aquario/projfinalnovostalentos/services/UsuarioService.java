package com.aquario.projfinalnovostalentos.services;

import com.aquario.projfinalnovostalentos.models.Usuario;
import com.aquario.projfinalnovostalentos.repositories.UsuarioRepository;
import com.aquario.projfinalnovostalentos.utils.LoginForm;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    
    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario login(LoginForm loginForm) throws Exception{
        
        Optional<Usuario> checkUsuario = this.repository.findByEmail(loginForm.getEmail());
        if(checkUsuario.isPresent()){
            Usuario usuario = checkUsuario.get(); 
            if(usuario.getSenha().equals(usuario.getSenha())){
                return usuario;
            }
            else{
                throw new Exception("Senha inválida");
            }
        }
        else{
            throw new Exception("E-mail inválido");
        }
    }

    public Usuario register(Usuario usuarioForm) throws Exception{
        
        Optional<Usuario> checkUsuario = this.repository.findByEmail(usuarioForm.getEmail());
        if(!checkUsuario.isPresent()){
           return this.repository.save(usuarioForm); 

        }
        else{
            throw new Exception("E-mail já cadastrado");
        }
    }

    
}
