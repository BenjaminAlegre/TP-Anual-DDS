package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.utilsImportador;


import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeEmision;

import java.io.IOException;
import java.util.List;

public interface ImportadorDeDatos {

    public List<DatoDeEmision> importarDatos(String path) throws IOException;
}