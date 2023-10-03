package model.entities.ranking;


import model.entities.entidades.Entidad;
import model.repositorios.rankings.RepositorioTiempoDeCierre;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class RankTiempoDeCierre extends RankTemplateMethod {

    @Transient
    RepositorioTiempoDeCierre repo = new RepositorioTiempoDeCierre();

    @Override
    public void generarRanking(List<Entidad> entidades) {

    }

    @Override
    protected void guardarse() {
        this.repo.guardar(this);
    }


    @Override
    protected void rankear(List<Entidad> entidades) {

    }
}
