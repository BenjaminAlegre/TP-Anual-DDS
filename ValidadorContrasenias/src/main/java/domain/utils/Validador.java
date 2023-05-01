package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Validador implements ValidadorDeContrasenias {
    private List<Validacion> listaDeValidaciones = new ArrayList<>() ;
    private static Validador instancia = null;

    public static Validador getInstancia(){
        if(instancia == null) {
            instancia = new Validador();
         //   this.cargarValidacionesPorDefecto();
        }
        return  instancia;
    }
/*
    public void cargarValidacionesPorDefecto() {
        Validacion longitud = new ValidadorLongitud(8);//TODO
        Validacion caracateres = new Validad

    }*/

    public void agregarValidacion(Validacion validacion){
        listaDeValidaciones.add(validacion);
    }
    public void quitarValidacion(Validacion validacion){
        listaDeValidaciones.remove(validacion);
    }

    public Validador(Validacion... validaciones) {
        this.listaDeValidaciones = new ArrayList<>();
        for (Validacion v : validaciones) {
            this.listaDeValidaciones.add(v);
        }
    }

    public List<RespuestaValidacion> validarContrasenia(String contrasenia) {
        List<RespuestaValidacion> resultados = new ArrayList<>();
        for (int i = 0; i < this.listaDeValidaciones.size(); i++) {
            if(!listaDeValidaciones.get(i).validar(contrasenia)) {
                resultados.add(new RespuestaValidacion(listaDeValidaciones.get(i).mensajeDeError()));
            }
        }
        return resultados;
    }

    class RespuestaValidacion{
        public String mensaje;

        public RespuestaValidacion(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
