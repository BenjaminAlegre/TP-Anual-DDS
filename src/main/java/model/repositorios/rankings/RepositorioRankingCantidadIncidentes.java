package model.repositorios.rankings;

import db.EntityManagerHelper;
import model.entities.ranking.RankCantidadIncidentes;


import java.util.List;

public class RepositorioRankingCantidadIncidentes {

    public List<RepositorioRankingCantidadIncidentes> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + this.getClass().getName())
                .getResultList();
    }

    public RankCantidadIncidentes buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(RankCantidadIncidentes.class, id);
    }

    public void guardar(RankCantidadIncidentes rank) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(rank);
        EntityManagerHelper.commit();
    }
}
