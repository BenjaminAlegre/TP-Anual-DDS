package model.entities.notificacion;

public interface Reportador {

    Incidente generarIncidente(Incidente incidente);

    void cerrarIncidente(Incidente incidente);

}
