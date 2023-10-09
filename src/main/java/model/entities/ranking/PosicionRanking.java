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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private RankStrategy ranking;

    @OneToOne(fetch = FetchType.EAGER)
    private Entidad entidad;
    
    private Integer posicion;
}
