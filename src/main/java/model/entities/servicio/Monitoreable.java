package model.entities.servicio;

import model.entities.incidentes.Incidente;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Monitoreable {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "monitoreable")
    List<Incidente> incidentes;

    @Column
    private String funcionamientoHabitual;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public String getFuncionamientoHabitual() {
        return funcionamientoHabitual;
    }

    public void setFuncionamientoHabitual(String funcionamientoHabitual) {
        this.funcionamientoHabitual = funcionamientoHabitual;
    }
}
