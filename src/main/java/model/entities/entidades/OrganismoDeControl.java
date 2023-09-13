package model.entities.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue("organismoDeControl")
public class OrganismoDeControl extends PersonaJuridica{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
