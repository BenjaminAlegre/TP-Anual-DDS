package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import java.util.regex.Pattern;

public class ValidadorCaracteresEspeciales implements Validacion{
    String regex = ".*[^\\w].*";

    public ValidadorCaracteresEspeciales(){
    }

    @Override
    public boolean validar(String contrasenia) {
        return Pattern.matches(regex, contrasenia);
    }
    @Override
    public String mensajeDeError(){
        return "-La contraseña debe contener caracteres especiales, ej:@!?.;<>.\n";
    }

}
