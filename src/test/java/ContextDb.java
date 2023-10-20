import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import model.entities.comunidad.TipoMiembro;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembros;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

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

                for (int i = 1; i <= 3; i++) {
                        Comunidad comunidad = new Comunidad();
                        repositorioComunidades.agregar(comunidad);
                }





        }


}
