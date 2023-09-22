package model.entities.ranking;

import javax.persistence.Column;
import java.time.LocalDate;




public abstract class RankStrategy {


/*
    @Transient
    private List<Incidente> incidentes;
*/

    private LocalDate fecha;



    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
