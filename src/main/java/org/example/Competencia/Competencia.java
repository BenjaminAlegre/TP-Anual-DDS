package org.example.Competencia;

import org.example.Corredor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competencia_id;

    @Column
    private Date fecha;

    @OneToMany(mappedBy="competencia_id", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Corredor> corredores;

    public Integer getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(Integer competencia_id) {
        this.competencia_id = competencia_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }
}
