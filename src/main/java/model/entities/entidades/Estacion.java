package model.entities.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ESTACION")
public class Estacion extends Establecimiento {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LineaDeTransporte> lineas;
}
