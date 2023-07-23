import model.entities.entidades.adapters.adapters.ServicioGeoDds;
import model.entities.entidades.adapters.entidadesDeNormalizacion.Direccion;
import model.entities.entidades.adapters.entidadesDeNormalizacion.ListadoPosiblesDirecciones;
import model.entities.entidades.adapters.entidadesDeNormalizacion.Provincia;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestNormalizacion {

    public ServicioGeoDds servicioAPI = new ServicioGeoDds();

    public TestNormalizacion() throws Exception {
    }

    @Test
    public void obtenerProvincias() throws Exception {


        List<Provincia> provincias = servicioAPI.listadoProvincias();
        for (Provincia p : provincias
        ) {
            System.out.println(p.nombre);
        }
        System.out.println(provincias.size());
    }

    @Test
    public void normalizarDireccion() throws IOException {
        ListadoPosiblesDirecciones direccionesNormalizadas = servicioAPI.normalizarDireccion("avenida santa fe 1270");
        for (Direccion d: direccionesNormalizadas.direcciones
             ) {
            System.out.println(d.toString());

        }
    }
}