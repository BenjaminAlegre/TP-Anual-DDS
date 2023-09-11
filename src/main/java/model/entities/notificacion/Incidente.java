package model.entities.notificacion;

import model.entities.incidentes.EstadoIncidente;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;

@Entity
public class Incidente {

    @Id
    private Integer id;

    @OneToOne
    private Monitoreable servicioAfectado;

    @Column
    private String observaciones;

    @Enumerated(EnumType.STRING)
    private EstadoIncidente estado;
}
