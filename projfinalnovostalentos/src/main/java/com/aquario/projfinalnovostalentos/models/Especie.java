package com.aquario.projfinalnovostalentos.models;

import javax.persistence.*;

import com.aquario.projfinalnovostalentos.utils.FileUpload;

@Entity
@Table(name="especie")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = true)
    private String foto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String alimento;

    @ManyToOne
    @JoinColumn(name="aquario_pk", nullable = false)
    private Aquario aquario;

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

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getAlimento() {
        return this.alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public Aquario getAquario() {
        return this.aquario;
    }

    public void setAquario(Aquario aquario) {
        this.aquario = aquario;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", nome='" + getNome() + "'" +
            ", foto='" + getFoto() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", alimento='" + getAlimento() + "'" +
            ", aquario='" + getAquario() + "'" +
            "}";
    }

   
}
