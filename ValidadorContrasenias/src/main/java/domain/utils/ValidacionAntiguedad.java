package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;


import mi_impacto_ambiental.models.entities.logueo.Cuenta;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ValidacionAntiguedad implements Validacion{

    private int antiguedad_tolerada;

    public ValidacionAntiguedad() throws IOException {
        Properties propiedades = new Properties();
//         InputStream entrada = new FileInputStream("propiedades.properties");
//         propiedades.load(entrada);
        this.antiguedad_tolerada = 90; // Integer.parseInt(propiedades.getProperty("ANTIGUEDAD_TOLERABLE_CONSTRASENIA"));
    }
    public ValidacionAntiguedad(int dias){
        this.antiguedad_tolerada = dias;
    }

    public boolean validar(Cuenta cuenta) {
        Date ahora = new Date(System.currentTimeMillis());
        long diff = cuenta.getFechaDeDefinicionContrasenia().getTime() - ahora.getTime();
        TimeUnit tiempo = TimeUnit.DAYS;
        return  tiempo.convert(diff, TimeUnit.MILLISECONDS) >= antiguedad_tolerada;
    }

    @Override
    public boolean validar(String contrasenia) {
        return false;
    }

    public String mensajeDeError() {
        return "Debe cambiar su contraseña \n";
    }
}
