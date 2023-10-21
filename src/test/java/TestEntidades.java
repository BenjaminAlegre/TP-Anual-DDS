import model.entities.entidades.Entidad;
import model.entities.notificacion.Incidente;
import model.repositorios.RepositorioEntidades;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestEntidades {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    @Test
    public void testCargarIncidentes(){
//        Entidad entidad = repositorioEntidades.buscarPorId(1);
//        Incidente incidente = repositorioIncidentes.buscarPorId(1);
//
//        entidad.setIncidentes(repositorioIncidentes.buscarPorEntidad(entidad));
//

    }

}
