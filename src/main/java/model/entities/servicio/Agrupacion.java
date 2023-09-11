package model.entities.servicio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "agrupacion")
@DiscriminatorValue("agrupacion")
public class Agrupacion extends Monitoreable {




}
