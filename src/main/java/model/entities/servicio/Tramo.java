package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Setter@Getter
@Entity
public class Tramo {

    @Id
    private int idTramo;

    @Enumerated(EnumType.STRING)
    private PuntosTramo puntoOrigen;

    @Enumerated(EnumType.STRING)
    private PuntosTramo puntoFinal;

    public Tramo(int idTramo, PuntosTramo puntoOrigen, PuntosTramo puntoFinal) {
        this.idTramo = idTramo;
        this.puntoOrigen = puntoOrigen;
        this.puntoFinal = puntoFinal;
    }

    public Tramo() {
    }

}
