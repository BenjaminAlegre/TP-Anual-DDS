import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import model.entities.comunidad.TipoMiembro;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembros;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMiembro {


    private static EntityManagerFactory emf;
    private static EntityManager em;



    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

    @Test
    public void testAgregarMiembros() {

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

        Assertions.assertNotNull(repositorioMiembros.buscarPorId(1));
  }

    @Test
    public void probarContacto(){
//        miembro1.setMedioNotificacion(MedioNotificacion.WHATSAPP);
//        Assertions.assertEquals(miembro1.contacto(), "+541138157280");
//        miembro1.setMedioNotificacion(MedioNotificacion.CORREO_ELECTRONICO);
//        Assertions.assertEquals(miembro1.contacto(), "leo_messi@gmail.com");
    }

    @Test
    public void testAgregarComunidades(){
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Comunidad comunidad1 = new Comunidad();
        Comunidad comunidad2 = new Comunidad();

        repositorioComunidades.agregar(comunidad1);
        repositorioComunidades.agregar(comunidad2);

        List<Comunidad> comunidades = repositorioComunidades.buscarTodos();
        assertEquals(2, comunidades.size());
    }

    @Test
    public void testVerificarComunidades(){
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
        List<Comunidad> comunidades = repositorioComunidades.buscarTodos();
        assertEquals(2, comunidades.size());
    }

//FUncionan con properties en update
    @Test
    public void testAgregarMiembroAComunidad(){

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Miembro m2 = repositorioMiembros.buscarPorId(2);

        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);

        comunidad1.agregarMiembro(m1);
        comunidad1.agregarMiembro(m2);

        comunidad2.agregarMiembro(m1);

        repositorioComunidades.agregar(comunidad1);
        repositorioComunidades.agregar(comunidad2);

    }

    @Test
    public void testAgregarAdministradorAComunidad(){

        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(3);
        Miembro m2 = repositorioMiembros.buscarPorId(5);

        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        Comunidad comunidad2 = repositorioComunidades.buscarPorId(3);

        comunidad1.agregarAdministrador(m1);
        comunidad1.agregarAdministrador(m2);

        comunidad2.agregarAdministrador(m1);

        repositorioComunidades.agregar(comunidad1);
        repositorioComunidades.agregar(comunidad2);

    }

    @Test//En teoria funciona pero no persiste la relacion
    public void testAgregarComunidadAMiembro(){
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Miembro m2 = repositorioMiembros.buscarPorId(2);

        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        Comunidad comunidad2 = repositorioComunidades.buscarPorId(3);

        m1.asociarseAComunidad(comunidad1);
        m1.asociarseAComunidad(comunidad2);

        m2.asociarseAComunidad(comunidad2);

        repositorioMiembros.agregar(m1);
        repositorioMiembros.agregar(m2);

        Assertions.assertEquals(comunidad1.getId(), m1.getComunidades().get(0).getId());
    }

}
