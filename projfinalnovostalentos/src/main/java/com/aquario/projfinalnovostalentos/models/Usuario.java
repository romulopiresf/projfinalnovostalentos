package com.aquario.projfinalnovostalentos.models;

import javax.persistence.*;

import com.aquario.projfinalnovostalentos.utils.FileUpload;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = true)
    private String foto;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    public String getImagem(){
        if(foto == null || pk == 0)
            return null;
        try{
            return FileUpload.fileName(this, foto);
        } catch(Exception e ){
            return null;
        }
        
    }

    public long getPk() {
        return this.pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", nome='" + getNome() + "'" +
            ", senha='" + getSenha() + "'" +
            ", foto='" + getFoto() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }



    
}
