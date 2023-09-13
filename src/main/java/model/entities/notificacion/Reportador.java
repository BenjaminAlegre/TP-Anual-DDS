package model.entities.notificacion;

import javax.persistence.Entity;

public interface Reportador {

    public Incidente generarIncidente();

    public void cerrarIncidente();
}
