package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import java.util.regex.Pattern;

public class ValidadorMayusuculas implements Validacion{
  String regex = ".*[A-Z].*";

    public ValidadorMayusuculas() {
    }

    @Override
    public boolean validar(String contrasenia) {
       return Pattern.matches(regex, contrasenia);
    }

  @Override
  public String mensajeDeError(){
    return "-La contraseña debe contener Mayusuculas. \n";
  }

}
