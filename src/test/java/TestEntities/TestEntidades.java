package TestEntities;

import model.entities.entidades.Entidad;
import model.entities.entidades.Organizacion;
import model.entities.notificacion.Incidente;
import model.repositorios.RepositorioEntidades;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static model.entities.notificacion.EstadoIncidente.ACTIVO;

public class TestEntidades {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();

    private Incidente incidente;


    @Test
    public void testEntraEnCalculoSemanal() {
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);

        incidente = incidentes.get(0);

        // Prueba el método entraEnCalculoSemanal con una fecha actual
        LocalDate fechaActual = LocalDate.now().minusDays(1);
        boolean result = incidente.entraEnCalculoSemanal(fechaActual);

        // El incidente esté activo y dentro de la fecha actual
        Assertions.assertTrue(result);
    }

    @Test
    public void testObtenerIncidentes(){
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);

        Assertions.assertEquals(incidentes.size(), 1);
    }

    @Test
    public void testTiempoDeCierre() {
        // Obtener Incidente de la BD
        Entidad entidad = repositorioEntidades.buscarPorId(1);
        List<Incidente> incidentes = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);
        incidente = incidentes.get(0);

        // Prueba el método tiempoDeCierre y verifica el resultado
        double tiempoCierre = incidente.tiempoDeCierre();

        // Esto da -5?
        Assertions.assertEquals(-5, tiempoCierre, 0.001);
    }

    @Test
    public void testReportableAMiembro() {
        //TODO
    }

    @Test
    public void traerEntidadesPorTipo(){
        List<Entidad> entidades = repositorioEntidades.buscarPorTipo("" +
                "BANCO");
        for (Entidad e: entidades
             ) {
            System.out.println(e.getNombre());
        }
    }

}
