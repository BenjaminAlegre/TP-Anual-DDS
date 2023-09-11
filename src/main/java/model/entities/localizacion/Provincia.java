package model.entities.localizacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Provincia extends Localizacion {

    @Id
    private Integer idProvincia;
    @Id
    private String codigoDeProvincia;

    @Column
    private String nombre;

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

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
