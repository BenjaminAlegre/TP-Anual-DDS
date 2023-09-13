package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // justificacion: como solo hay 3 atributos distintos creemos que la mejor opcion es in single table
public abstract class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstablecimiento;

    @Column
    private String nombre;

    @OneToOne
    private Direccion ubicacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establecimiento",cascade = CascadeType.ALL)
    private List<Monitoreable> monitoreables;



}
