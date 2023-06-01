package model.entities.entidades;

public class Organizacion extends Entidad{
    public Organizacion(String nombre, String cuit, String telefono, String personaEncargada) {
        this.setNombre(nombre);
        this.setCuit(cuit);
        this.setTelefono(telefono);
        this.setPersonaEncargada(personaEncargada);

    }
}
