package model.entities.servicio;

import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.entidades.Establecimiento;
import model.entities.notificacion.Incidente;

import javax.persistence.*;
import java.util.List;

@Getter@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "tipo")
public abstract class Monitoreable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @OneToMany(mappedBy = "servicioAfectado")
    private List<Incidente> incidentes;

    @Column
    private String funcionamientoHabitual;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "miembros_monitoreables",
            joinColumns = @JoinColumn(name="monitoreable_id"),
            inverseJoinColumns=@JoinColumn(name="miembro_id"))
    private List<Miembro> miembros;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Establecimiento establecimiento;

}
