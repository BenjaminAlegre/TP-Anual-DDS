package model.entities.comunidad;

import model.entities.incidentes.EstadoIncidente;
import model.entities.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Comunidad {

    private List<Miembro> miembros;
    private List<Miembro> administradores;
    private List<Incidente> incidentes;

    public Comunidad(List<Miembro> miembros, List<Miembro> administradores, List<Incidente> incidentes) {
        this.miembros = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
    }
    public void agregarAdministrador(Miembro administrador) {
        administradores.add(administrador);
    }
    public void eliminarMiembro(Miembro miembro) {
        miembros.remove(miembro);
    }
    public void eliminarAdministrador(Miembro administrador) {
        administradores.remove(administrador);
    }
    public void agregarIncidentes(List<Incidente> incidentes){
        this.incidentes.addAll(incidentes);
    }

    public List<Incidente> consultarIncidentesPorEstado(EstadoIncidente estado){
        return this.incidentes.stream().filter(incidente -> incidente.getEstado() == estado).collect(Collectors.toList());
    }
}
