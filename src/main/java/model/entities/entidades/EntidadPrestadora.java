package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;

import model.entities.notificacion.*;

import model.entities.notificacion.Incidente;
import model.entities.notificacion.Observable;
import model.entities.notificacion.Suscriber;
import model.entities.servicio.Monitoreable;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@DiscriminatorValue("entidadPresadora")

public class EntidadPrestadora extends PersonaJuridica implements Observable, Reportador {




    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "suscripciones",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="suscriptor_id"))
    private List<Miembro> suscriptores = new ArrayList<>();


    public void agregarSuscriptor(Miembro miembro){
        suscriptores.add(miembro);
    }

    @Override
    public void notificar(){
        this.suscriptores.forEach(suscriptor -> suscriptor.serNotificadoPor(this));

    }

    @Override
    public Incidente generarIncidente(){
        Incidente incidente = new Incidente();
        incidente.setReportador(this.getId().toString());
        incidente.setEstado(EstadoIncidente.ACTIVO);
        incidente.setHorarioApertura(java.time.LocalDate.now());
        incidente.setObservaciones(null);
        incidente.setServicioAfectado(null);
        return incidente;
    }

    @Override
    public Incidente generarIncidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, String idReportador, Entidad entidadAfectada) {
        return null;
    }

    @Override
    public void cerrarIncidente(Incidente incidente){
        //No debe hacer nada

    }
}
