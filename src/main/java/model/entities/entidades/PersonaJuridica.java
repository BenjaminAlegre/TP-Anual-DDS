package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPersonaJuridica")
public abstract class PersonaJuridica extends EntidadPersistente {



    @Column
    private String nombre;
    @Column
    private Integer cuit;
    @Column
    private Integer telefono;
    @Column
    private String personaAsignada;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personaJuridica", cascade = CascadeType.ALL)
    public List<Entidad> entidades;


    public void mostrarInforme(){
        //TODO
    }

}
