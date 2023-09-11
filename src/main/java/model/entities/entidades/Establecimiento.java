package model.entities.entidades;

import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Establecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstablecimiento;

    @Column
    private String nombre;

    @ManyToOne
    private Direccion ubicacion;

    @OneToMany
    private List<Monitoreable> monitoreables;

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Direccion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Monitoreable> getMonitoreables() {
        return monitoreables;
    }

    public void setMonitoreables(List<Monitoreable> monitoreables) {
        this.monitoreables = monitoreables;
    }
}
