package model.entities.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Organizacion")
public class Organizacion extends Entidad{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoOrganizacion tipo;

    @OneToMany
    private List<Sucursal> sucursales;

}
