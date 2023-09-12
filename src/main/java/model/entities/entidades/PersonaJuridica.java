package model.entities.entidades;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PersonaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonaJuridica;
    @Column
    private String nombre;
    @Column
    private Integer cuit;
    @Column
    private Integer telefono;
    @Column
    private String personaAsignada;

    public Integer getIdPersonaJuridica() {
        return idPersonaJuridica;
    }

    public void setIdPersonaJuridica(Integer idPersonaJuridica) {
        this.idPersonaJuridica = idPersonaJuridica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCuit() {
        return cuit;
    }

    public void setCuit(Integer cuit) {
        this.cuit = cuit;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getPersonaAsignada() {
        return personaAsignada;
    }

    public void setPersonaAsignada(String personaAsignada) {
        this.personaAsignada = personaAsignada;
    }

    /*
    TODO falta base de datos
    public Ranking mostrarInforme(){

    }

     */
}
