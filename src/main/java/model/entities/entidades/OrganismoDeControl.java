package model.entities.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("organismoDeControl")
public class OrganismoDeControl extends PersonaJuridica{



}
