package model.entities.notificacion;



import lombok.Getter;
import lombok.Setter;
//import model.entities.comunidad.Comunidad;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.servicio.Monitoreable;

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
    private EstadoIncidente estado;

    @Column
    private LocalDate horarioApertura;
    @Column
    private LocalDate horarioCierre;


    @ManyToOne
    @JoinColumn(name = "idEntReportador")
    private EntidadPrestadora entidadReportadora;
    @ManyToOne
    @JoinColumn(name = "idMiembroReportador")
    private Miembro miembroReportador;
    @ManyToOne
    @JoinColumn(name = "idEntidadAfectada")
    private Entidad entidadAfectada;

    //@ManyToMany(mappedBy = "incidentes")
    //private List<Comunidad> comunidades;
}
