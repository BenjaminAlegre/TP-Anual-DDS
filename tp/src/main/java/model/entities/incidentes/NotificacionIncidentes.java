package model.entities.incidentes;

import java.util.ArrayList;
import java.util.List;

public class NotificacionIncidentes {
    private List<Incidente> incidentes;
    public NotificacionIncidentes() {
        this.incidentes = new ArrayList<>();
    }

    public void actualizar(List<Incidente> incidentes){
        this.incidentes.addAll(incidentes);
    }

}
