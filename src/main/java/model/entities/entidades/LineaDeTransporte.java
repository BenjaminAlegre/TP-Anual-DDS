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


    @OneToOne
    private Estacion origen;

    @OneToOne
    private Estacion destino;

    @Enumerated(EnumType.STRING)
    private Transporte tipo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "estaciones_lineas",
            joinColumns = @JoinColumn(name="linea_id"),
            inverseJoinColumns=@JoinColumn(name="estacion_id"))
    private List<Estacion> estaciones;

}
