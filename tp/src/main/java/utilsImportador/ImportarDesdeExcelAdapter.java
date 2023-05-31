package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.utilsImportador;


import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeEmision;

import java.io.IOException;
import java.util.List;

public class ImportarDesdeExcelAdapter implements ImportadorDeDatos{

    private ImportadorExcel adaptada = new ImportadorExcel();

    public List<DatoDeEmision> importarDatos(String path) throws IOException{

        return adaptada.importarDatos(path);
    }
 //   public void mostrarListaResultado(List<DatoDeEmision> datosRegistrados){
   //     adaptada.mostrarListaResultado(datosRegistrados);
    //}
    public ImportadorExcel getAdaptada() {
        return adaptada;
    }

    public void setAdaptada(ImportadorExcel adaptada) {
        this.adaptada = adaptada;
    }
}
