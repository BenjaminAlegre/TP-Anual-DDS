package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
*/
@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidad")
public abstract class Entidad {

    @Id
    private String id;
    @Column
    private String nombre;

  //  @OneToOne
    //private EntidadPrestadora entidadPrestadora; // TODO esto esta mal

    //@OneToOne
  //  private OrganismoDeControl organismoDeControl;// TODO esto esta mal
/*
    @OneToMany
    private List<Suscriber> suscribers;
*/
    @ManyToMany
    private List<EntidadPrestadora> suscripciones;


}
