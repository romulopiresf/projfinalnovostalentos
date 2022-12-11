package com.aquario.projfinalnovostalentos.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pk;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name="aquario_pk", nullable = false)
    private Aquario aquario;

    @OneToMany(mappedBy = "equipamento")
    private Set<HistoricoEquipamento> historico = new HashSet<>();

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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
            ", status='" + isStatus() + "'" +
            ", id='" + getId() + "'" +
            ", aquario='" + getAquario() + "'" +
            "}";
    }
    
   
}
