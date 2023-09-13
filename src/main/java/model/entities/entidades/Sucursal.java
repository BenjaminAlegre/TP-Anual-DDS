package model.entities.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SUCURSAL")
public class Sucursal extends Establecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Organizacion organizacion;
}
