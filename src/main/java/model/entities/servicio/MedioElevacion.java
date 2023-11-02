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

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "idTramo")
//    private Tramo tramo;

    private String tramo;

    public MedioElevacion() {

    }

    @Override
    public String descripcion() {
        return "Medio de elevación tipo "+ this.tipo.toString()+" ubicado en "+ super.getEstablecimiento().descripcion();
    }

    @Override
    public String tipo() {
        return this.getClass().getSimpleName()+" "+ tipo.label +" "+ tramo;
    }

    public MedioElevacion(TipoDeElevacion tipo, String tramo) {
        this.tipo = tipo;
        this.tramo = tramo;
    }
}