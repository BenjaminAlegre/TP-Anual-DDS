package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioRankingCantidadIncidentes;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class RankCantidadIncidentes extends RankTemplateMethod {

    RepositorioRankingCantidadIncidentes repo = new RepositorioRankingCantidadIncidentes();

    @Override
    protected void guardarse() {
        repo.guardar(this);
    }

    @Override
    protected void rankear(List<Entidad> entidades) {
        List<Entidad> ordenadas = new ArrayList<Entidad>(entidades);
        ordenadas.sort(Comparator.comparing(Entidad::cantidadIncidentes));
        RankCantidadIncidentes aPersistir = new RankCantidadIncidentes();
        this.setRanking(ordenadas);
    }

}
