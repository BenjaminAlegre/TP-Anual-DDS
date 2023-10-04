package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Getter@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Localizacion extends EntidadPersistente {



    @Column
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localizacion", cascade = CascadeType.ALL)
    private List<Entidad> entidades;

   // @OneToMany(fetch = FetchType.EAGER, mappedBy = "localizacion", cascade = CascadeType.ALL)
   // private List<Miembro> miembros;

}
