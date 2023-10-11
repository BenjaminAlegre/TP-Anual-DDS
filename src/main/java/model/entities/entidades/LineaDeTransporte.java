package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("LineaDeTransporte")
public class LineaDeTransporte extends Entidad{


    @Enumerated(EnumType.STRING)
    private Transporte tipo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "estaciones_lineas",
            joinColumns = @JoinColumn(name="estacion_id"),
            inverseJoinColumns=@JoinColumn(name="linea_id"))
    private List<Estacion> estaciones;

}
