package model.entities.ranking;

import model.entities.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Rankeador {
    private List<Incidente> incidentes;

    public Rankeador(List<Incidente> incidentes) {
        this.incidentes = new ArrayList<>();
    }
}
