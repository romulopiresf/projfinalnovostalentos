package com.aquario.projfinalnovostalentos.models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name="historico_equipamento")
public class HistoricoEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Time hora;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name="equipamento_pk", nullable = false)
    private Equipamento equipamento;

    @Transient
    public String getEstado(){
        if(isStatus())
            return "Ligado";
        else
            return "Desligado";        
    }

    public long getPk() {
        return this.pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
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

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Equipamento getEquipamento() {
        return this.equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }


    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", data='" + getData() + "'" +
            ", hora='" + getHora() + "'" +
            ", status='" + isStatus() + "'" +
            ", equipamento='" + getEquipamento() + "'" +
            "}";
    }
    
   
}
