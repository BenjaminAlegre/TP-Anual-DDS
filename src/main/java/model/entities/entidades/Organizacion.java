package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter@Setter
@Entity
@DiscriminatorValue("Organizacion")
public class Organizacion extends Entidad{


    @Enumerated(EnumType.STRING)
    private TipoOrganizacion tipo;

    @OneToMany
    private List<Sucursal> sucursales;

}
