package model.entities.notificacion;



import lombok.Getter;
import lombok.Setter;
//import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.servicio.Monitoreable;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
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
    private LocalDate horarioApertura = LocalDate.now();
    @Column
    private LocalDate horarioCierre;

    @ManyToOne
    @JoinColumn(name = "id")
    private String reportador;
    @ManyToOne
    @JoinColumn(name = "id")
    private Entidad entidadAfectada;

    @ManyToMany(mappedBy = "incidentes")
    private List<Comunidad> comunidades;

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

    public Double tiempoDeCierre(){

                Period periodo = Period.between(horarioCierre, horarioApertura);
                double diasDiferencia = periodo.getDays() + periodo.getMonths() * 30.44 + periodo.getYears() * 365.25;
                return diasDiferencia;
    }

    public Incidente() {
    }

    public Incidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, String idReportador, Entidad entidadAfectada) {
        this.servicioAfectado = servicioAfectado;
        this.observaciones = observaciones;
        this.estado = estado;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.reportador = idReportador;
        this.entidadAfectada = entidadAfectada;
    }

}
