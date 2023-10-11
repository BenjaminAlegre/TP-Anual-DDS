package model.entities.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("provincia")
public class Provincia extends Localizacion {



    @OneToMany(fetch = FetchType.LAZY)
    private List<Departamento> departamentos;
    public void traerContenido(){

    }

}
