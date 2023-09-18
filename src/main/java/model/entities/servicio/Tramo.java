package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Setter@Getter
@Entity
public class Tramo {

    @Id
    private int idTramo;

    @Enumerated
    private PuntosTramo puntoOrigen;

    @Enumerated
    private PuntosTramo puntoFinal;



}
