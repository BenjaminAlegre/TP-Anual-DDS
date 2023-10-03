package model.repositorios.rankings;

import Cron.db.EntityManagerHelper;
import model.entities.ranking.RankTiempoDeCierre;

import java.util.List;

public class RepositorioTiempoDeCierre {
    public List<RepositorioTiempoDeCierre> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + RepositorioTiempoDeCierre.class.getName())
                .getResultList();
    }

    public RepositorioTiempoDeCierre buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(RepositorioTiempoDeCierre.class, id);
    }

    public void guardar(RankTiempoDeCierre rank) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(rank);
        EntityManagerHelper.commit();
    }

}
