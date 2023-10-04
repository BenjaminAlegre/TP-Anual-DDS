package model.repositorios;

import Cron.db.EntityManagerHelper;
import model.entities.comunidad.Miembro;

import java.time.LocalDate;
import java.util.List;

public class RepositorioMiembros {

    public void agregar(Miembro miembro) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.commit();
    }

    public List<Miembro> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Miembro.class.getName())
                .getResultList();
    }

    public Miembro buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, id);
    }

    public List<Miembro> obtenerMimebrosPorHorarioNotificacion(LocalDate horario){

            return null;
//                    EntityManagerHelper
//                    .getEntityManager()
//                    .createQuery("from " + Miembro.class.getName() + " where"+ horario.toString() + "in horario="  + "and procesado = false")
//                    .getResultList();

    }
}
