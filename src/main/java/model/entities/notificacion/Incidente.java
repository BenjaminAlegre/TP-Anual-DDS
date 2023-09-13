package model.entities.notificacion;



import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Comunidad;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "incidentes")
    private List<Comunidad> comunidades;
}
