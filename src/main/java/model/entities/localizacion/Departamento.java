package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorValue("departamento")
public class Departamento extends Localizacion{


    @ManyToOne
    @JoinColumn(name = "idProvincia")
    private Provincia provincia;

    @Column
    private String nombre;

}
