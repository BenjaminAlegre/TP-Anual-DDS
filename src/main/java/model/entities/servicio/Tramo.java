package model.entities.servicio;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Tramo {

    @Id
    private int idTramo;

    @Enumerated
    private PuntosTramo puntoOrigen;

    @Enumerated
    private PuntosTramo puntoFinal;

    public int getId() {
        return idTramo;
    }

    public void setId(int id) {
        this.idTramo = id;
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
