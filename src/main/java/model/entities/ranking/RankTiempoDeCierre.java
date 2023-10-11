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
public class RankTiempoDeCierre extends RankStrategy {

    @Transient
    RepositorioTiempoDeCierre repo = new RepositorioTiempoDeCierre();



    @Override
    protected void rankear(List<Entidad> entidades) {
        List<Entidad> ordenadas = new ArrayList<Entidad>(entidades);
        ordenadas.sort(Comparator.comparing(e->e.cantidadIncidentesSemanales(this.getFecha().minusDays(7))));
        super.genearPosiciones(ordenadas);
        this.repo.guardar(this);
    }
}
