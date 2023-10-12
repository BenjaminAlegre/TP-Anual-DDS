package model.entities.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SUCURSAL")
public class Sucursal extends Establecimiento {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Organizacion organizacion;

    @Override
    public Entidad entidad() {
        return this.organizacion;
    }

    @Override
    public String descripcion() {
        return "Sucursal "+ this.getNombre()+" de "+ this.organizacion.getNombre()+" ubicada en "+ this.getUbicacion().toString();
    }


}
