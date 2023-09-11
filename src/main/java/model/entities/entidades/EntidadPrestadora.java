package model.entities.entidades;

import model.entities.incidentes.Reportador;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("entidadPresadora")
public class EntidadPrestadora extends PersonaJuridica implements Reportador {
}
