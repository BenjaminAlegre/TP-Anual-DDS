package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter@Getter
@Entity
@DiscriminatorValue("organismoDeControl")
public class OrganismoDeControl extends PersonaJuridica{


}
