package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;

import java.util.List;

public class RepositorioComunidades {
    public void agregar(Comunidad com) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(com);
        EntityManagerHelper.commit();
    }

    public List<Comunidad> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Comunidad.class.getName())
                .getResultList();
    }

    public Comunidad buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Comunidad.class, id);
    }
}
