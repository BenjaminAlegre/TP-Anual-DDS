package model.entities.notificacion;

public interface Reportador {

    public Incidente generarIncidente();

    public void cerrarIncidente(Incidente incidente);

}
