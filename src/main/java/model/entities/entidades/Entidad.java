package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;
import model.entities.localizacion.Localizacion;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidad")
public abstract class Entidad {

    @Id
    private String id;
    @Column
    private String nombre;

    @ManyToOne
    private Localizacion localizacion;

    @OneToOne
    @JoinColumn(name = "idEntidadPrestadora")
    private EntidadPrestadora entidadPrestadora; // TODO esto esta mal

    @OneToOne
    @JoinColumn(name = "idOrganismoDeControl")
    private OrganismoDeControl organismoDeControl;// TODO esto esta mal


}
