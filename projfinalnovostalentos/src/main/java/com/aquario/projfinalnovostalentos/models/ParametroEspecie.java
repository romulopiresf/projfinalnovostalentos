package com.aquario.projfinalnovostalentos.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="parametro_especie")
public class ParametroEspecie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name="especie_pk", nullable = false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name="parametro_pk", nullable = false)
    private Parametro parametro;


    public long getPk() {
        return this.pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Especie getEspecie() {
        return this.especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Parametro getParametro() {
        return this.parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", valor='" + getValor() + "'" +
            ", especie='" + getEspecie() + "'" +
            ", parametro='" + getParametro() + "'" +
            "}";
    }

}
