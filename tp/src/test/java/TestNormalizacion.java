import normalizacionDirecciones.adapters.adapters.ServicioGeoDds;
import normalizacionDirecciones.adapters.entidadesDeNormalizacion.*;
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