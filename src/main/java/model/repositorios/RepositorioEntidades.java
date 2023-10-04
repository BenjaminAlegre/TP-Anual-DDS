package model.repositorios;

import Cron.db.EntityManagerHelper;
import model.entities.entidades.Entidad;

import java.util.List;

public class RepositorioEntidades {

    public void agregar(Entidad entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(entidad);
        EntityManagerHelper.commit();
    }

    public List<Entidad> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Entidad.class.getName())
                .getResultList();
    }

    public Entidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Entidad.class, id);
    }



}
