package model.entities.notificacion;

import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.servicio.Monitoreable;

import java.time.LocalDate;

public interface Reportador {

    public Incidente generarIncidente();

    public Incidente generarIncidente(Monitoreable servicioAfectado, String observaciones,
                                      EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre,
                                      String idReportador, Entidad entidadAfectada); // string reportador se desnormaliza y se pesiste solo el id sera un atributo de session
                                                                                        // que se puede tomar en cuallqueirmomento en el controller

    public void cerrarIncidente(Incidente incidente);

}
