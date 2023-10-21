package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@DiscriminatorValue("medio_elevacion")
public class MedioElevacion extends Servicio {

    @Enumerated( EnumType.STRING)
    private TipoDeElevacion tipo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idTramo")
    private Tramo tramo;

    @Override
    public String descripcion() {
        return "Medio de elevación tipo "+ this.tipo.toString()+" ubicado en "+ super.getEstablecimiento().descripcion();
    }
}