package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("entidadPresadora")
public class EntidadPrestadora extends PersonaJuridica {


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "suscripciones",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="suscriptor_id"))
    private List<Miembro> suscriptores;
}
