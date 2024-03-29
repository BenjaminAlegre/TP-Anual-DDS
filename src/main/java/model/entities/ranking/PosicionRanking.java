package model.entities.ranking;

import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.Entidad;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class PosicionRanking extends EntidadPersistente {


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private RankStrategy ranking;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Entidad entidad;

    private Integer posicion;

    public PosicionRanking(RankStrategy ranking, Entidad entidad, Integer posicion) {
        this.ranking = ranking;
        this.entidad = entidad;
        this.posicion = posicion;
    }

    public PosicionRanking() {
    }
}
