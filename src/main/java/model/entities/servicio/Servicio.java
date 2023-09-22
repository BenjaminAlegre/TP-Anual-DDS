package model.entities.servicio;


import javax.persistence.*;

@Entity
@DiscriminatorValue("servicio")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipoServicio")
public abstract class Servicio extends Monitoreable {






}