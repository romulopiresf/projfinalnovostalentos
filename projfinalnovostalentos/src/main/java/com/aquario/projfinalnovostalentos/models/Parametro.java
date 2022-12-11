package com.aquario.projfinalnovostalentos.models;

import javax.persistence.*;

@Entity
@Table(name="paremetro")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private float minimo;

    @Column(nullable = false)
    private float maximo;


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

    public float getMinimo() {
        return this.minimo;
    }

    public void setMinimo(float minimo) {
        this.minimo = minimo;
    }

    public float getMaximo() {
        return this.maximo;
    }

    public void setMaximo(float maximo) {
        this.maximo = maximo;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", nome='" + getNome() + "'" +
            ", minimo='" + getMinimo() + "'" +
            ", maximo='" + getMaximo() + "'" +
            "}";
    }
    
   
}
