package model.entities.localizacion;

import javax.persistence.*;

@Entity
@DiscriminatorValue("municipio")
public class Municipio extends Localizacion{


    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "idProvincia")
    private Provincia provincia;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
