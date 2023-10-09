package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioRankingsImpacto;
import javax.persistence.Transient;
import java.util.List;

public class RankImpacto extends RankStrategy {

    @Transient
    RepositorioRankingsImpacto repo = new RepositorioRankingsImpacto();

    @Override
    protected void guardarse() {
        this.repo.guardar(this);
    }

    @Override
    protected void rankear(List<Entidad> entidades) {

    }
}
