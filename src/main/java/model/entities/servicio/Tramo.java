package model.entities.servicio;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Tramo {

    @Id
    private int id;

    @Enumerated
    private PuntosTramo puntoOrigen;

    @Enumerated
    private PuntosTramo puntoFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PuntosTramo getPuntoOrigen() {
        return puntoOrigen;
    }

    public void setPuntoOrigen(PuntosTramo puntoOrigen) {
        this.puntoOrigen = puntoOrigen;
    }

    public PuntosTramo getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(PuntosTramo puntoFinal) {
        this.puntoFinal = puntoFinal;
    }
}
