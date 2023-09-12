package model.entities.localizacion;

import javax.persistence.*;

@Entity
@DiscriminatorValue("departamento")
public class Departamento extends Localizacion{


    @ManyToOne
    @JoinColumn(name = "idProvincia")
    private Provincia provincia;

    @Column
    private String nombre;


    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
