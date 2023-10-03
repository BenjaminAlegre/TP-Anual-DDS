package model.entities.servicio;

import model.entities.comunidad.Miembro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agrupamiento")
@DiscriminatorValue("agrupamiento")
public class Agrupamiento extends Monitoreable {


    @Transient
    private List<Monitoreable> componentes;

    public Agrupamiento(List<Monitoreable> componentes) {
        this.componentes = new ArrayList<>();
    }

    public Agrupamiento() {
    }

    public void agregarMonitoreable(Monitoreable monitoreable) {
        componentes.add(monitoreable);
    }

    public void eliminarMonitoreable(Monitoreable monitoreable) {
        componentes.remove(monitoreable);
    }

}
