package model.repositorios;

import db.EntityManagerHelper;
import model.entities.comunidad.Miembro;
import model.entities.servicio.Servicio;
import model.entities.servicio.Tramo;

import java.util.List;

public class RepositorioServicios {
    public void agregar(Servicio servicio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(servicio);
        EntityManagerHelper.commit();
    }

    public List<Servicio> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Servicio.class.getName())
                .getResultList();
    }

    public Servicio buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, id);
    }


}
