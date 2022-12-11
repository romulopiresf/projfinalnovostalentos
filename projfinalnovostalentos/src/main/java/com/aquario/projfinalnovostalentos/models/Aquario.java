package com.aquario.projfinalnovostalentos.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

import com.aquario.projfinalnovostalentos.utils.FileUpload;

@Entity
@Table(name="aquario")
public class Aquario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = true)
    private String foto;

    @Column(nullable = false)
    private int largura;

    @Column(nullable = false)
    private int altura;

    @Column(nullable = false)
    private int profundidade;

    @Column(nullable = false)
    private float volume;

    @Transient
    public String getImagem(){
        if(foto == null || pk == 0)
            return null;
        try{
            return FileUpload.fileName(null, foto);
        } catch(Exception e ){
            return null;
        }
        
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "aquario_usuario", 
        joinColumns = { @JoinColumn(name="aquario_pk")},
        inverseJoinColumns = {@JoinColumn(name="usuario_pk")}
    )
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "aquario")
    private Set<Equipamento> equipamentos = new HashSet<>();
    
    public void addUsuario(Usuario usuario){
        if(!usuarios.contains(usuario)){
            usuarios.add(usuario);
            usuario.addAquario(this);
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

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getLargura() {
        return this.largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getProfundidade() {
        return this.profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", nome='" + getNome() + "'" +
            ", foto='" + getFoto() + "'" +
            ", largura='" + getLargura() + "'" +
            ", altura='" + getAltura() + "'" +
            ", profundidade='" + getProfundidade() + "'" +
            ", volume='" + getVolume() + "'" +
            ", usuarios='" + getUsuarios() + "'" +
            "}";
    }

    

    
   
}
