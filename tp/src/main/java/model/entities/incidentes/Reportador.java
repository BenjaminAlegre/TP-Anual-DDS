package model.entities.incidentes;

import model.entities.servicio.Monitoreable;

import java.time.LocalDate;

public interface Reportador {

    default void generarIncidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, Reportador reportador, String entidadAfectada){
        new Incidente(servicioAfectado, observaciones, estado, horarioApertura, horarioCierre, reportador, entidadAfectada);
        //TODO ver logica de notificaciones
    }


}
