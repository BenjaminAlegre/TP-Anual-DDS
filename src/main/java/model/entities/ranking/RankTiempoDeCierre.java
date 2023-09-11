package model.entities.ranking;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TiempoDeCierre")
public class RankTiempoDeCierre extends RankStrategy{
}
