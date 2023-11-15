package model.repositorios.incidentes;

import db.EntityManagerHelper;
import model.entities.entidades.Entidad;
import model.entities.notificacion.Incidente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public List<Incidente> buscarPorNombreEntidad(String nombre){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from "+ Incidente.class.getName()+" where entidadAfectada_id="+nombre).getResultList();
    }

    public List<Incidente> buscarIncidentesPorEntidad(Entidad entidad) {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        // Usamos JPQL para realizar la consulta
        String jpql = "SELECT i FROM Incidente i WHERE i.entidadAfectada = :entidad";
        TypedQuery<Incidente> query = entityManager.createQuery(jpql, Incidente.class);
        query.setParameter("entidad", entidad);

        // Ejecutamos la consulta y devolvemos la lista de incidentes
        return query.getResultList();
    }

    public List<Incidente> obtenerTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from "+Incidente.class.getName()).getResultList();
    }

    public Incidente buscarPorId(Integer id){
        return EntityManagerHelper.getEntityManager().find(Incidente.class, id);
    }

    public void eliminar(Incidente incidente){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(incidente);
        EntityManagerHelper.commit();
    }
    public void actualizar(Incidente incidente){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(incidente);
        EntityManagerHelper.commit();
    }
}
