package model.entities.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("entidadPresadora")
public class EntidadPrestadora extends PersonaJuridica {
}
