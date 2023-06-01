package cargaDeDatosMasiva;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.Entidad;
import model.entities.entidades.Organizacion;
import model.entities.entidades.OrganismoDeControl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Importador implements ImportadorCSVAdaptada {
   private Integer contadorFilas = 0;
    public void generarObjetos(String path) throws CsvValidationException, IOException {
        this.leerArchivo(path);
    }

    private void leerArchivo(String path) throws IOException, CsvValidationException {
       // List<Entidad> entidades = new ArrayList<>();
        String archCSV = "archivo.csv";
        CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(archCSV)).withCSVParser(conPuntoYComa).build();

        String[] fila = reader.readNext();
        fila = reader.readNext();
        while (fila != null) {
            this.contadorFilas++;
            Entidad instancia = this.crearInstancia(fila); //TODO aca se espera que se verifi si ya existe en el sistema, se actualice o se cree.
          //  if(instancia !=null)
          //  entidades.add(instancia);
            fila = reader.readNext();
        }
        reader.close();
    }

    private Entidad crearInstancia(String[] fila) {
        Entidad entidad = null;
        switch (fila[0]) {
            case "ORGANISMO_DE_CONTROL":
                entidad = new OrganismoDeControl(fila[1], fila[2], fila[3]);
                break;
            case "ENTIDAD_PRESTADORA":
                entidad = new Organizacion(fila[1], fila[2], fila[3]);
            default:
                System.out.println("no se pudo crear entidad de fila "+contadorFilas);
        }

        for (int i = 0; i < fila.length; i++) {
            System.out.println(fila[i]);
        }
        return entidad;

    }


    public Importador() {

    }
}
