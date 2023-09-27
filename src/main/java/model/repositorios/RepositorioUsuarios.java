package model.repositorios;

import Cron.db.EntityManagerHelper;
import model.entities.comunidad.Usuario;


import java.util.List;

public class RepositorioUsuarios {
    public List<Usuario> buscarTodos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Usuario.class.getName())
                .getResultList();
    }

    public Usuario buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, id);
    }


}
