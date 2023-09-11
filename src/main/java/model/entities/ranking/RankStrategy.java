package model.entities.ranking;

import model.entities.notificacion.Incidente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RankStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdRank;

    @Transient
    private List<Incidente> incidentes;

    @Column
    private LocalDate fecha;



}
