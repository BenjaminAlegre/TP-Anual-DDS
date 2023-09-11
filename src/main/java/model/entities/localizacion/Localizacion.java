package model.entities.localizacion;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Localizacion {
    @Id
    private Integer id;

    @Column
    private String nombre;


}
