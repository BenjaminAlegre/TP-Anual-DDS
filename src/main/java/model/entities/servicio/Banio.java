package model.entities.servicio;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
@DiscriminatorValue("banio")
public class Banio extends Servicio {

    @Enumerated( EnumType.STRING )
    private TipoDeBanio tipo;


}