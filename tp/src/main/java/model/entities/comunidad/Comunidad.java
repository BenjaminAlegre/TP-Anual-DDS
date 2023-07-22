package model.entities.comunidad;

import model.entities.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {

    private List<Miembro> miembros;
    private List<Miembro> administradores;
    private List<Incidente> incidentes;

    public Comunidad(List<Miembro> miembros, List<Miembro> administradores, List<Incidente> incidentes) {
        this.miembros = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }
}
