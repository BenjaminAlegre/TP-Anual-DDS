package model.entities.localizacion;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto es para una clave primaria autoincremental
    private Integer idLocalizacion;

    @Column
    private String nombre;

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
