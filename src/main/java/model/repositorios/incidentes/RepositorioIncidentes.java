package model.repositorios.incidentes;

import db.EntityManagerHelper;
import model.entities.entidades.Entidad;
import model.entities.notificacion.Incidente;

import java.util.List;

public class RepositorioIncidentes {

    public void guardar(Incidente entidad) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(entidad);
        EntityManagerHelper.commit();
    }

    public List<Incidente> buscarPorEntidad(Entidad entidad){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from "+ Incidente.class.getName()+" where entidadAfectada_id="+entidad.getId()).getResultList();
    }
}
