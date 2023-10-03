package model.entities.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SUCURSAL")
public class Sucursal extends Establecimiento {

    @ManyToOne
    private Organizacion organizacion;
}
