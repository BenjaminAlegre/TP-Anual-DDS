package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.Incidente;
import model.entities.persistencia.EntidadPersistente;
import model.entities.ranking.RankStrategy;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidad")
public abstract class Entidad extends EntidadPersistente {

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id")
    private Localizacion localizacion;

    @ManyToOne
    @JoinColumn(name = "id")
    public PersonaJuridica personaJuridica;


    @OneToMany(mappedBy = "entidadAfectada", fetch = FetchType.LAZY)
    private List<Incidente> incidentes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "posisionesRankeadas",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="ranking_id"))
    public List<RankStrategy> rankings = new ArrayList<>();

    private List<Incidente> traerIncidentesValidos(LocalDate fecha){
        return
        this.incidentes.stream().filter(i-> i.entraEnCalculoSemanal(fecha)).collect(Collectors.toList());
    }

    public Integer cantidadIncidentesSemanales(LocalDate fecha) {
        List<Incidente> incidentesValidos = traerIncidentesValidos(fecha);
        Map<Monitoreable, Incidente> resultado = incidentesValidos.stream()
                .collect(Collectors.toMap(
                        Incidente::getServicioAfectado,
                        objeto -> objeto,
                        (obj1, obj2) -> obj1 // Resolver conflictos manteniendo el primer objeto
                ));

        return new ArrayList<Incidente>(resultado.values()).size();

    }

    public Double tiempoPromedioDeCierreIncidentes(LocalDate fechaDeInicioRanking){
        List<Incidente> incidentesValidos = traerIncidentesValidos(fechaDeInicioRanking).stream().filter(i -> i.getHorarioCierre() != null).collect(Collectors.toList());
       Double total = 0.0;
        for (Incidente i: incidentesValidos) {
            total =+ i.tiempoDeCierre();
        }
        return total / incidentesValidos.size();

    }


}
