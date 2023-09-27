package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.Incidente;
import model.entities.notificacion.Suscriber;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("entidadPresadora")
public class EntidadPrestadora extends PersonaJuridica implements Suscriber {



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "suscripciones",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="suscriptor_id"))
    private List<Miembro> suscriptores = new ArrayList<>();




    public void agregarSuscriptor(Miembro miembro){
        suscriptores.add(miembro);
    }

    public void notificar(){
        //TODO
    }
}
