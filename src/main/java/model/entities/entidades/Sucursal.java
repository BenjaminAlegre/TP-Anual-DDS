package model.entities.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SUCURSAL")
public class Sucursal extends Establecimiento {

    @ManyToOne
    private Organizacion organizacion;
}
