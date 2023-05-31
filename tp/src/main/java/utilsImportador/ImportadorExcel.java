package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.utilsImportador;

import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.Actividad;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeEmision;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeLogistica;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportadorExcel {

    CreadorDatoActividad creadorDatos = new CreadorDatoActividad();

    public List<DatoDeEmision> importarDatos(String path) throws IOException {
        List<DatoDeEmision> datosRegistrados = new ArrayList<>();
        Workbook libro = crearWorkBook(path);
        Iterator<Row> fila = libro.getSheetAt(0).rowIterator();
        fila.next();
        fila.next();


        while (fila.hasNext()) {
            Row row = fila.next();
            DataFormatter dataFormatter = new DataFormatter();
            String valor;
            Iterator<Cell> celdas = row.cellIterator();
            DatoDeEmision dato ;
            Cell celda = celdas.next();
            valor = dataFormatter.formatCellValue(celda);
//            System.out.println(valor);
            dato = creadorDatos.cargarDatoActividad(valor, fila, celdas);
           datosRegistrados.add(dato);

//            System.out.println("Termino fila, largo de lista: " + datosRegistrados.size());
        }

        return datosRegistrados;
    }

    public void mostrarListaResultado(List<DatoDeEmision> datosRegistrados) {
        for(DatoDeEmision d: datosRegistrados) {
            if (d.getActividad() == Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS) {
                System.out.println(d.getActividad().toString());
                System.out.println(d.getPeriodicidad().toString());
                System.out.println(d.getPeriodoDeImputacion().toString());
                System.out.println(((DatoDeLogistica)d).getVehiculo().toString());
                System.out.println(((DatoDeLogistica) d).getDistanciaRecorrida());// esto hace ruido
                System.out.println(((DatoDeLogistica)d).getPeso());

            } else {
                System.out.println(d.getActividad().toString());
                System.out.println(d.getTipo().toString());
                System.out.println(d.getValor().toString());
                System.out.println(d.getPeriodicidad().toString());
                System.out.println(d.getPeriodoDeImputacion().toString());
            }
        }

    }

    private Workbook crearWorkBook(String path) throws IOException {
        File f = new File(path);
        InputStream input = new FileInputStream(f);
       return WorkbookFactory.create(input);
    }

}

