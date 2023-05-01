package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import lombok.Getter;
import lombok.Setter;

public class ValidadorLongitud implements Validacion{
    @Getter @Setter private Integer longitud;

    public ValidadorLongitud(Integer longitud){
        this.setLongitud(longitud);
    }

    @Override
    public boolean validar(String contrasenia) {
        return contrasenia.length() >= this.longitud;
    }

    @Override
    public String mensajeDeError(){
        return "-La contraseña debe contener al menos " + longitud + " caracteres.\n";
    }
}
