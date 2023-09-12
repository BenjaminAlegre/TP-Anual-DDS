package model.entities.servicio;

import javax.persistence.*;

@Entity
@DiscriminatorValue("medio_elevacion")
public class MedioElevacion extends Servicio {

    @Enumerated
    private TipoDeElevacion tipo;

    @OneToOne
    @JoinColumn(name = "idTramo")
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