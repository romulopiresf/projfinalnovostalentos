package com.aquario.projfinalnovostalentos.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="parametro_aquario")
public class ParametroAquario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name="aquario_pk", nullable = false)
    private Aquario aquario;

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

    public Aquario getAquario() {
        return this.aquario;
    }

    public void setAquario(Aquario aquario) {
        this.aquario = aquario;
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
            ", aquario='" + getAquario() + "'" +
            ", parametro='" + getParametro() + "'" +
            "}";
    }

}
