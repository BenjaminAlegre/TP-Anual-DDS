package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;
import model.entities.servicio.Monitoreable;
import model.entities.servicio.Servicio;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // justificacion: como solo hay 3 atributos distintos creemos que la mejor opcion es in single table
@DiscriminatorColumn(name = "tipoEstablecimiento")
public abstract class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @OneToOne
    private Direccion ubicacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establecimiento",cascade = CascadeType.ALL)
    private List<Monitoreable> monitoreables;


    public void agregarMonitoreable(Monitoreable monitoreable){
        monitoreables.add(monitoreable);
    }

    public void quitarMonitoreable(Monitoreable monitoreable){
        monitoreables.remove(monitoreable);
    }


}
