package model.entities.comunidad;

import model.entities.notificacion.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComunidad;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Miembro> miembros;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Miembro> administradores;

    @OneToMany//(mappedBy = "comunidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
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

    public Integer getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(Integer idComunidad) {
        this.idComunidad = idComunidad;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public List<Miembro> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Miembro> administradores) {
        this.administradores = administradores;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
}
