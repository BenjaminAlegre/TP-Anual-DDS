package model.repositorios;

import db.EntityManagerHelper;
import model.entities.servicio.Servicio;

public class RepositorioEstablecimientos {

    public Servicio buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Servicio.class, id);
    }
}
