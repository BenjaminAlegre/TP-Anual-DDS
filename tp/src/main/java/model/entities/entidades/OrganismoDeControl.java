package model.entities.entidades;

public class OrganismoDeControl extends Entidad{


    public OrganismoDeControl(String nombre, String cuit, String telefono, String personaEncargada) {
        this.setNombre(nombre);
        this.setCuit(cuit);
        this.setTelefono(telefono);
        this.setPersonaEncargada(personaEncargada);

    }
}
