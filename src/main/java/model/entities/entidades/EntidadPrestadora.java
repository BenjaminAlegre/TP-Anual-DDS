package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.*;

import javax.persistence.*;
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
        incidente.setEntidadReportadora(this);
        incidente.setEstado(EstadoIncidente.ACTIVO);
        incidente.setHorarioApertura(java.time.LocalDate.now());
        incidente.setObservaciones(null);
        incidente.setServicioAfectado(null);
        return incidente;
    }

    @Override
    public void cerrarIncidente(Incidente incidente){
        //No debe hacer nada
    }
}
