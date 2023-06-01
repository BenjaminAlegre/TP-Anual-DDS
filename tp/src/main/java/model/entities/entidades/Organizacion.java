package model.entities.entidades;

public class Organizacion extends Entidad{
    public Organizacion(String nombre, String telefono, String personaEncargada) {
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setPersonaEncargada(personaEncargada);

    }
}
