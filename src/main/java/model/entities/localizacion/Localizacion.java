package model.entities.localizacion;

import model.entities.comunidad.Miembro;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto es para una clave primaria autoincremental
    private Integer id;

    @Column
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "localizacion", cascade = CascadeType.ALL)
    private List<Miembro> miembros;

}
