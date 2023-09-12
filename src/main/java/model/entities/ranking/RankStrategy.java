package model.entities.ranking;

//import model.entities.notificacion.Incidente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;



public abstract class RankStrategy {


/*
    @Transient
    private List<Incidente> incidentes;
*/
    @Column
    private LocalDate fecha;



    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
