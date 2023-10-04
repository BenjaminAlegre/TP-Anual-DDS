package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioTiempoDeCierre;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class RankTiempoDeCierre extends RankTemplateMethod {

    @Transient
    RepositorioTiempoDeCierre repo = new RepositorioTiempoDeCierre();


    @Override
    protected void guardarse() {
        this.repo.guardar(this);
    }


    @Override
    protected void rankear(List<Entidad> entidades) {
        List<Entidad> ordenadas = new ArrayList<Entidad>(entidades);
        LocalDate fechaDeInicioRanking = this.getFecha().minusDays(7);
        ordenadas.sort(Comparator.comparing(e->e.cantidadIncidentesSemanales(fechaDeInicioRanking)));
        RankCantidadIncidentes aPersistir = new RankCantidadIncidentes();
        this.setRanking(ordenadas);
    }
}
