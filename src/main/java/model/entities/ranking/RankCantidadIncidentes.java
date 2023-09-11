package model.entities.ranking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RankCantidadIncidentes")
public class RankCantidadIncidentes extends RankStrategy{
}
