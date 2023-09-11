package model.entities.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Direccion {

    @Id
    private Integer id;
    @Column
    private String calle;

    @Column
    private Integer altura;
}
