package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import java.util.regex.Pattern;

public class ValidadorNumeros implements Validacion{
    String regex = ".*[0-9].*";

    public ValidadorNumeros(){
    }

    @Override
    public boolean validar(String contrasenia) {
        return Pattern.matches(regex, contrasenia);
    }

    @Override
    public String mensajeDeError(){
        return "-La contraseña debe contener Digitos.\n";
    }

}
