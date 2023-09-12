package model.entities.entidades;

import model.entities.notificacion.Suscriber;

import javax.persistence.*;
import java.util.List;

/*
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
*/
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Entidad {

    @Id
    private String idEntidad;
    @Column
    private String nombre;

    @OneToOne
    private EntidadPrestadora entidadPrestadora;

    @OneToOne
    private OrganismoDeControl organismoDeControl;
/*
    @OneToMany
    private List<Suscriber> suscribers;
*/
    @ManyToMany
    private List<EntidadPrestadora> suscripciones;

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EntidadPrestadora getEntidadPrestadora() {
        return entidadPrestadora;
    }

    public void setEntidadPrestadora(EntidadPrestadora entidadPrestadora) {
        this.entidadPrestadora = entidadPrestadora;
    }

    public OrganismoDeControl getOrganismoDeControl() {
        return organismoDeControl;
    }

    public void setOrganismoDeControl(OrganismoDeControl organismoDeControl) {
        this.organismoDeControl = organismoDeControl;
    }
/*
    public List<Suscriber> getSuscribers() {
        return suscribers;
    }

    public void setSuscribers(List<Suscriber> suscribers) {
        this.suscribers = suscribers;
    }
*/
    public List<EntidadPrestadora> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<EntidadPrestadora> suscripciones) {
        this.suscripciones = suscripciones;
    }
}
