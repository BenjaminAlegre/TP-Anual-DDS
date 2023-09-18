package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
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


}
