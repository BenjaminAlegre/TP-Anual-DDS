package model.entities.servicio;

import model.entities.incidentes.Incidente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Entity
@DiscriminatorValue("banio")
public class Banio extends Servicio {

    @Enumerated( EnumType.STRING )
    private TipoDeBanio tipo;

    public TipoDeBanio getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeBanio tipo) {
        this.tipo = tipo;
    }
}