package model.entities.servicio;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;

@Entity
@DiscriminatorValue("servicio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Servicio extends Monitoreable {






}