package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;



import lombok.Getter;
import mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.external.GestorArchivoContrasenias;
import mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.external.Nodo;

import java.io.IOException;
@Getter
public class ValidadorDebilidad implements Validacion{
    private GestorArchivoContrasenias gestorDeArchivoDeContrasenias ;// cambiar a private

    public ValidadorDebilidad(String path, Validacion ... validaciones) throws IOException {
        this.gestorDeArchivoDeContrasenias = new GestorArchivoContrasenias(path, validaciones);
        this.gestorDeArchivoDeContrasenias.obtenerContraseniasUtiles();
    }
    public boolean validar(String contrasenia){
        boolean coincide = false;
        Nodo aux = gestorDeArchivoDeContrasenias.arbolDeBusqueda.raiz;
        while(aux!= null && !coincide){
            if(contrasenia.compareTo(aux.info) == 0){
                coincide = true;
            }
            else{
                if(contrasenia.compareTo(aux.info) < 0)
                    aux = aux.izquierda;
                else
                    aux = aux.derecha;
            }
        }
        return !coincide;
    }
    @Override
    public String mensajeDeError(){
        return "La contraseña está en el top de contraseñas débiles.\n";
    }

}
