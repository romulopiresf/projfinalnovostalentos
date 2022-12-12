package com.aquario.projfinalnovostalentos.models;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="acao")
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private String sugestao;

    @ManyToOne
    @JoinColumn(name="aquario_pk", nullable = false)
    private Aquario aquario;

    @ManyToOne
    @JoinColumn(name="parametro_pk", nullable = true)
    private Parametro parametro;

    @ManyToOne
    @JoinColumn(name="equipamento_pk", nullable = true)
    private Equipamento equipamento;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Time hora;

    @Transient
    public String getAcao(){
        String acao = sugestao;
        if(parametro != null)
            acao += " o parâmetro " + parametro.getNome();
        if(equipamento != null)
            acao += " o equipamento " + equipamento.getNome();
        acao += " no aquario " + aquario.getNome();
        return acao;
    }

    public long getPk() {
        return this.pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getSugestao() {
        return this.sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
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

    public Equipamento getEquipamento() {
        return this.equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }


    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return this.hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", sugestao='" + getSugestao() + "'" +
            ", aquario='" + getAquario() + "'" +
            ", parametro='" + getParametro() + "'" +
            ", equipamento='" + getEquipamento() + "'" +
            ", data='" + getData() + "'" +
            ", hora='" + getHora() + "'" +
            "}";
    }

    

}
