package model.entities.incidentes;

import model.entities.servicio.Monitoreable;

import java.time.LocalDate;

public class Incidente {

    private Monitoreable servicioAfectado;
    private String observaciones;
    private EstadoIncidente estado;
    private LocalDate horarioApertura;
    private LocalDate horarioCierre;
    private Reportador reportador;
    private String entidadAfectada;

    public Incidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, Reportador reportador, String entidadAfectada) {
        this.servicioAfectado = servicioAfectado;
        this.observaciones = observaciones;
        this.estado = estado;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.reportador = reportador;
        this.entidadAfectada = entidadAfectada;
    }
    public void generarNotificacion(){
        //TODO
    }

}
