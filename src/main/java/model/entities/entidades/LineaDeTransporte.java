package model.entities.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LineaDeTransporte")
public class LineaDeTransporte extends Entidad{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer idLineaDeTransporte;
    @OneToOne
    private Estacion origen;

    @OneToOne
    private Estacion destino;

    @Enumerated(EnumType.STRING)
    private Transporte tipo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estacion> estaciones;


    public Estacion getOrigen() {
        return origen;
    }

    public void setOrigen(Estacion origen) {
        this.origen = origen;
    }

    public Estacion getDestino() {
        return destino;
    }

    public void setDestino(Estacion destino) {
        this.destino = destino;
    }

    public Transporte getTipo() {
        return tipo;
    }

    public void setTipo(Transporte tipo) {
        this.tipo = tipo;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
}
