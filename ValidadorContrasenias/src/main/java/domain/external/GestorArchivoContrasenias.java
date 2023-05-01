package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.external;


import mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils.Validacion;
import mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils.Validador;
import lombok.Setter;
import lombok.var;

import java.io.*;

public class GestorArchivoContrasenias {

    private Validador criterioDeFiltrado;
    @Setter
    private String pathArchivo;
    public Arbol  arbolDeBusqueda;

    public GestorArchivoContrasenias(String path, Validacion... validaciones) throws IOException {
        this.arbolDeBusqueda = new Arbol();
        this.setPathArchivo(path);
        Validador validador = new Validador(validaciones);
        this.criterioDeFiltrado = validador;
        // obtenerContraseniasUtiles();
        // this.arbolDeBusqueda.recorrer(arbolDeBusqueda.raiz);
    }

    public void obtenerContraseniasUtiles() throws IOException {
        System.out.print("Obteniendo contraseñas debiles de archivo... \n");
        File archivo = new File(this.pathArchivo);
        BufferedReader entrada = new BufferedReader(new FileReader(archivo));
        var lectura = entrada.readLine();
        while(lectura != null) {
            if (criterioDeFiltrado.validarContrasenia(lectura).size() == 0) {
                //System.out.println(lectura);
                this.arbolDeBusqueda.insertarNodo(lectura); // agrega el nodo con la contrasenia al arbol de busqueda
            }
            lectura = entrada.readLine();
        }
        entrada.close();
    }
}
