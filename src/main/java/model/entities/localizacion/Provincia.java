package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("provincia")
public class Provincia extends Localizacion {


    @Column
    public String codigoDeProvincia;

    @Column
    public String nombre;


}
