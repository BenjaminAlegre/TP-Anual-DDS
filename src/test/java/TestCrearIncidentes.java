import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.Organizacion;
import model.entities.entidades.TipoOrganizacion;
import model.entities.localizacion.Localizacion;
import model.entities.localizacion.Provincia;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Banio;
import model.entities.servicio.TipoDeBanio;
import model.repositorios.RepositorioEntidades;
import model.repositorios.incidentes.RepositorioIncidentes;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCrearIncidentes {

    Miembro miembro = new Miembro("Leo", "Messi", "leo_messi@gmail.com", "+541138157280");


    Banio banioCoto = new Banio(TipoDeBanio.HOMBRES);
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();


    @Test
    public void crearCoto(){
        Organizacion coto = new Organizacion();
        coto.setNombre("COTO");
        coto.setTipo(TipoOrganizacion.SUPERMERCADO);
        //coto.setLocalizacion(new Provincia());
    }
    @Test
    public void crearIncidentePorMiembro(){
        Incidente incidente = new Incidente("Leo Messi", banioCoto, "Se tapo el baño");
        repositorioIncidentes.guardar(incidente);
//        Incidente incidenteTraido = repositorioIncidentes.buscarIncidentesPorEntidad()
        Entidad entidad = repositorioEntidades.buscarPorId(2);
        incidente.setEntidadAfectada(entidad);
        repositorioIncidentes.guardar(incidente);

    }

    @Test
    public void traerIncidentesPorEntidad(){
        Entidad entidad = repositorioEntidades.buscarPorId(2);
        List<Incidente> incidentesTraido = repositorioIncidentes.buscarIncidentesPorEntidad(entidad);
        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.ACTIVO);
        Assert.assertEquals(incidentesTraido.size(), 1);
    }


    @Test
    public void traerIncidentesPorEstado(){
        List<Incidente> incidentesTraido = repositorioIncidentes.buscarPorEstado("CERRADO");
        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.CERRADO);
        Assert.assertEquals(incidentesTraido.size(), 4);
    }

//    @Test
//    public void traerIncidentesPorEstadoYComunidad(){
//       // List<Incidente> incidentesTraido = repositorioIncidentes.buscarPorEstadoYComunidad("ACTIVO", 4);
//
//        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.ACTIVO);
////        Assert.assertEquals(incidentesTraido.get(0).getEstado(), EstadoIncidente.CERRADO);
//        System.out.println(incidentesTraido.get(0).getComunidades().get(0).getNombre());
////        System.out.println(incidentesTraido.get(0).getComunidades().get(0).getIncidentes().get(0).getEstado());
//    }


}
