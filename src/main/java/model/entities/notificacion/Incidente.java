package model.entities.notificacion;

import model.entities.servicio.Monitoreable;

import javax.persistence.*;

@Entity
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idMonitoreable")
    private Monitoreable servicioAfectado;

    @Column
    private String observaciones;

    @Enumerated(EnumType.STRING)
    private EstadoIncidente estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Monitoreable getServicioAfectado() {
        return servicioAfectado;
    }

    public void setServicioAfectado(Monitoreable servicioAfectado) {
        this.servicioAfectado = servicioAfectado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EstadoIncidente getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidente estado) {
        this.estado = estado;
    }
}
