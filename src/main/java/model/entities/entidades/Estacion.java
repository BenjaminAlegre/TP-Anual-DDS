package model.entities.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ESTACION")
public class Estacion extends Establecimiento {

    @ManyToMany(mappedBy = "estaciones")
    private List<LineaDeTransporte> lineas;
}
