package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.notificacion.Suscriber;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Comunidad implements Suscriber {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Miembro> miembros;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "miembros_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="miembro_id"))
    private List<Miembro> administradores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "incidentes_comunidad",
            joinColumns = @JoinColumn(name="comunidad_id"),
            inverseJoinColumns=@JoinColumn(name="incidente_id"))
    private List<Incidente> incidentes;

    public Comunidad(List<Miembro> miembros, List<Miembro> administradores, List<Incidente> incidentes) {
        this.miembros = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    public Comunidad() {

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
        return  this.incidentes.stream().filter(incidente -> incidente.getEstado().equals(estado)).collect(Collectors.toList());
    }

    public void notificar(){
        //TODO
    }

}
