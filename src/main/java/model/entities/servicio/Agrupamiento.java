package model.entities.servicio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "agrupamiento")
@DiscriminatorValue("agrupamiento")
public class Agrupamiento extends Monitoreable {


    @Transient
    private List<Monitoreable> componentes;


}
