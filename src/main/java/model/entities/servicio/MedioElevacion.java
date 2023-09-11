package model.entities.servicio;

import model.entities.incidentes.Incidente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@DiscriminatorValue("medio_elevacion")
public class MedioElevacion extends Servicio {

    @Enumerated
    private TipoDeElevacion tipo;

    @OneToOne
    private Tramo tramo;

    public TipoDeElevacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeElevacion tipo) {
        this.tipo = tipo;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }
}