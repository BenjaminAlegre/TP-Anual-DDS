package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioRankingCantidadIncidentes;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class RankCantidadIncidentes extends RankStrategy {

    @Transient
    RepositorioRankingCantidadIncidentes repo = new RepositorioRankingCantidadIncidentes();



    @Override
    protected void rankear(List<Entidad> entidades) {
        List<Entidad> ordenadas = new ArrayList<Entidad>(entidades);
        ordenadas.sort(Comparator.comparing(e->e.tiempoPromedioDeCierreIncidentes(this.getFecha().minusDays(7))));
        super.genearPosiciones(ordenadas);
        this.repo.guardar(this);
    }

}
