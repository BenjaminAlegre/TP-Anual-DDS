package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.utilsImportador;

import lombok.Getter;
import lombok.Setter;
import mi_impacto_ambiental.models.entities.UnidadDeCarbono;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.*;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeEmision;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoDeLogistica;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad.DatoElectrica;
import mi_impacto_ambiental.models.entities.organizacion.utils.huellaCarbono.factoresEmision.GestorDeFactores;
import mi_impacto_ambiental.models.entities.organizacion.utils.huellaCarbono.factoresEmision.GestorFactores;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

@Getter @Setter
public class CreadorDatoActividad {
     private Iterator<Row> filas;
     private Iterator<Cell> celdas;
     private String valor;
     private DataFormatter dataFormatter;
     private GestorFactores gestorFactores;

    public CreadorDatoActividad(){
        this.dataFormatter = new DataFormatter();
    }
    private Cell iterarCeldas(int veces){
        veces --;
        while(veces!=0){
            celdas.next();
            veces --;
        }
        System.out.println(" a punto de retornar "+ celdas.hasNext());
        return celdas.next();
    }

    private DatoDeLogistica crearDatoLogistica() {
        DatoDeLogistica dato = new DatoDeLogistica();
        Cell celda = iterarCeldas(2);
        valor = dataFormatter.formatCellValue(celda);
        dato.setCategoria(ProductoTransportado.valueOfProductoTransportados(valor));
        celda = iterarCeldas(1);
        valor = dataFormatter.formatCellValue(celda);
        dato.setPeriodicidad(Periodicidad.valueOfPeriodicidad(valor));
        celda = iterarCeldas(1);
        valor = dataFormatter.formatCellValue(celda);
        dato.setPeriodoDeImputacion(valor);
       // if(filas.hasNext())
         //   System.out.println("TIENE SIGUIENTE FILA");
        Row fila = filas.next();
        this.setCeldas(fila.cellIterator());
//        if(this.getCeldas() != null)
//           System.out.println("TIENE SIGUIENTE FILA"+ celdas.hasNext());
        celda = iterarCeldas(3);
        valor = dataFormatter.formatCellValue(celda);
        System.out.println(valor);
        dato.setVehiculo(TipoDeVehiculo.valueOfTipoDeVehiculo(valor));
        fila = filas.next();
        celdas = fila.cellIterator();
        celda = iterarCeldas(3);
        valor = dataFormatter.formatCellValue(celda);
        dato.setDistanciaRecorrida(valor);
        fila = filas.next();
        celdas = fila.cellIterator();
        celda = iterarCeldas(3);
        valor = dataFormatter.formatCellValue(celda);
        dato.setPeso(valor);
        dato.definirAnioYMes(dato.getPeriodoDeImputacion(),dato.getPeriodicidad());
        return dato;
    }

    public DatoDeEmision cargarDatoActividad(String valor, Iterator<Row> filas, Iterator<Cell> celdas ) {
        this.setFilas(filas);
        this.setCeldas(celdas);
        DatoDeEmision dato;

        switch(Actividad.valueOfActividad(valor)){
            case COMBUSTION_FIJA: dato = crearDatoSimple(Actividad.COMBUSTION_FIJA);
                break;
            case COMBUSTION_MOVIL: dato = crearDatoSimple(Actividad.COMBUSTION_MOVIL);
                break;
            case ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA: dato = crearDatoElectrica();
                break;
            case LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS: dato = crearDatoLogistica();
                break;
            default: throw new IllegalStateException("Unexpected value: "+ Actividad.valueOfActividad(valor));
        }
        return dato;

    }

    private DatoElectrica crearDatoElectrica() {
        DatoElectrica dato = new DatoElectrica();
        cargarRestoDeFila(dato);
        return dato;
    }

    private DatoDeEmision crearDatoSimple(Actividad nombreActividad) {
        DatoDeEmision dato = new DatoDeEmision();
        dato.setActividad(nombreActividad);
        cargarRestoDeFila(dato);
        return dato;
    }
    private void  cargarRestoDeFila(DatoDeEmision dato){

        Cell celda= celdas.next();
        valor = dataFormatter.formatCellValue(celda);
        dato.setTipo(TipoDeConsumo.valueOfTipo(valor));
        celda = celdas.next();
        valor = dataFormatter.formatCellValue(celda);
        dato.setValor(valor);
        celda = celdas.next();
        valor = dataFormatter.formatCellValue(celda);
        dato.setPeriodicidad(Periodicidad.valueOfPeriodicidad(valor));
        celda = celdas.next();
        valor = dataFormatter.formatCellValue(celda);
        dato.setPeriodoDeImputacion(valor);
        dato.definirAnioYMes(dato.getPeriodoDeImputacion(),dato.getPeriodicidad());
    }

    private void cargarFE(DatoDeEmision dato){

    }
}