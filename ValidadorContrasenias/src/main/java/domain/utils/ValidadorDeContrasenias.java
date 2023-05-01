package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.utils;

import java.util.List;

public interface ValidadorDeContrasenias {
    public List<Validador.RespuestaValidacion> validarContrasenia(String contrasenia);
   // public void cargarValidacionesPorDefecto() ;
}
