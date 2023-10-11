package model.repositorios.rankings;

import db.EntityManagerHelper;
import model.entities.ranking.RankImpacto;

import java.util.List;

public class RepositorioRankingsImpacto {

    public List<RankImpacto> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + RankImpacto.class.getName())
                .getResultList();
    }

    public RankImpacto buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(RankImpacto.class, id);
    }

    public void guardar(RankImpacto rank) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(rank);
        EntityManagerHelper.commit();
    }

}
