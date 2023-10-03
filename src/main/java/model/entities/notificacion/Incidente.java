package model.entities.notificacion;



import lombok.Getter;
import lombok.Setter;
//import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.servicio.Monitoreable;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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
    private EstadoIncidente estado = EstadoIncidente.ACTIVO;

    @Column
    private LocalDate horarioApertura;
    @Column
    private LocalDate horarioCierre;

    @ManyToOne
    @JoinColumn(name = "idEntReportador")//TODO maaaaaal
    private EntidadPrestadora entidadReportadora;
    @ManyToOne
    @JoinColumn(name = "idMiembroReportador")//TODO maaaaaal
    private Miembro miembroReportador;
    @ManyToOne
    @JoinColumn(name = "idEntidadAfectada")
    private Entidad entidadAfectada;

    public boolean entraEnCalculoSemanal(LocalDate fecha) {
        return this.estado.equals(EstadoIncidente.ACTIVO) & this.estaEnFecha(fecha);
    }

    private boolean estaEnFecha(LocalDate fecha) {
        return this.getHorarioApertura().isAfter(fecha);
    }

    public Incidente(Monitoreable servicioAfectado, String observaciones, LocalDate horarioApertura, Entidad entidadAfectada) {
        this.servicioAfectado = servicioAfectado;
        this.observaciones = observaciones;
        this.horarioApertura = horarioApertura;
        this.entidadAfectada = entidadAfectada;
    }
//@ManyToMany(mappedBy = "incidentes")
    //private List<Comunidad> comunidades;
}
