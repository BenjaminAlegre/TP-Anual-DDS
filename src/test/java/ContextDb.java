import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import model.entities.comunidad.TipoMiembro;
import model.entities.entidades.*;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.*;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioMiembros;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import model.repositorios.rankings.RepositorioTramo;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.entities.entidades.TipoOrganizacion.BANCO;
import static model.entities.entidades.Transporte.SUBTE;
import static org.junit.Assert.assertNotNull;

public class ContextDb  extends AbstractPersistenceTest implements WithGlobalEntityManager {

        @Test
        public void contextUp(){assertNotNull(entityManager());}

        @Test
        public void contextUpWithTransaction() throws Exception {
            withTransaction(() -> {});
        }

        @Test
        public void cargarDatos() {

                //Cargar miembros
                RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
                RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
                RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
                RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
                RepositorioServicios repositorioServicios = new RepositorioServicios();
                RepositorioTramo repositorioTramo = new RepositorioTramo();

                Miembro miembro1 = new Miembro("Leo", "Messi", "leo_messi@gmail.com", "+541138157280");
                Miembro miembro2 = new Miembro("Juan", "Pérez", "juan@example.com", "555-555-5555");

                miembro1.setTipo(TipoMiembro.OBSERVADOR);
                miembro1.setMedioNotificacion(MedioNotificacion.WHATSAPP);
                miembro2.setTipo(TipoMiembro.OBSERVADOR);
                miembro2.setMedioNotificacion(MedioNotificacion.WHATSAPP);

                repositorioMiembros.agregar(miembro1);
                repositorioMiembros.agregar(miembro2);

                for (int i = 1; i <= 10; i++) {
                        String nombre = "Nombre" + i;
                        String apellido = "Apellido" + i;
                        String correo = "correo" + i + "@example.com";
                        String telefono = "555-555-555" + i;

                        Miembro miembro = new Miembro(nombre, apellido, correo, telefono);

                        miembro.setTipo(TipoMiembro.AFECTADO);
                        miembro.setMedioNotificacion(MedioNotificacion.CORREO_ELECTRONICO);

                        // Agregar el miembro a la base de datos
                        repositorioMiembros.agregar(miembro);
                }

                //Cargar comunidades
                for (int i = 1; i <= 3; i++) {
                        Comunidad comunidad = new Comunidad();
                        Miembro miembroCom = repositorioMiembros.buscarPorId(i);
                        Miembro miembroAdmi = repositorioMiembros.buscarPorId(i+1);

                        List<Miembro> miembros = new ArrayList<>();
                        miembros.add(miembroCom);
                        List<Miembro> admins = new ArrayList<>();
                        admins.add(miembroAdmi);

                        comunidad.setMiembros(miembros);
                        comunidad.setAdministradores(admins);

                        repositorioComunidades.agregar(comunidad);
                }

                //Cargar Entidades
                for (int i = 1; i<= 5; i++){
                        LineaDeTransporte linea = new LineaDeTransporte();
                        Organizacion organizacion = new Organizacion();

                        linea.setNombre("Linea " + i);
                        linea.setTipo(SUBTE);

                        organizacion.setNombre("Organizacion " + i);
                        organizacion.setTipo(BANCO);

                        repositorioEntidades.agregar(linea);
                        repositorioEntidades.agregar(organizacion);
                }



                //Cargar Banios y MedioElevacion
                for (TipoDeBanio tipo : TipoDeBanio.values()) {
                        Banio banio = new Banio();
                        banio.setTipo(tipo);
                        repositorioServicios.agregar(banio);
                }

                Tramo tramo = new Tramo();
                tramo.setPuntoOrigen(PuntosTramo.ANDEN);
                tramo.setPuntoFinal(PuntosTramo.CALLE);
                repositorioTramo.guardarTramo(tramo);

                for (TipoDeElevacion tipo : TipoDeElevacion.values()) {
                        MedioElevacion medioElevacion = new MedioElevacion();
                        medioElevacion.setTipo(tipo);
                        repositorioServicios.agregar(medioElevacion);
                }

                //Cargar Incidente
                for (int i = 1; i<= 5; i++){
                        Incidente incidente = new Incidente();
                        incidente.setReportador("Reportador " + i);
                        incidente.setObservaciones("Observaciones " + i);
                        incidente.setEstado(EstadoIncidente.ACTIVO);
                        incidente.setHorarioApertura(LocalDate.now());
                        incidente.setHorarioCierre(LocalDate.now().plusDays(5));

                        Servicio servicio = repositorioServicios.buscarPorId(i);
                        Entidad entidadAfectada = repositorioEntidades.buscarPorId(i);

                        incidente.setServicioAfectado(servicio);
                        incidente.setEntidadAfectada(entidadAfectada);

                        repositorioIncidentes.guardar(incidente);
                }










        }


}
