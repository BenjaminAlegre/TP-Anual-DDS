package model.entities.localizacion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("provincia")
public class Provincia extends Localizacion {


    @Column
    private String codigoDeProvincia;

    @Column
    private String nombre;


    public String getCodigoDeProvincia() {
        return codigoDeProvincia;
    }

    public void setCodigoDeProvincia(String codigoDeProvincia) {
        this.codigoDeProvincia = codigoDeProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
