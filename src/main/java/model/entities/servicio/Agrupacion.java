package model.entities.servicio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "agrupacion")
@DiscriminatorValue("agrupacion")
public class Agrupacion extends Monitoreable {




}
