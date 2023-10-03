package model.entities.entidades;


import lombok.Getter;
import lombok.Setter;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.Incidente;
import model.entities.persistencia.EntidadPersistente;
import model.entities.ranking.RankTemplateMethod;
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
    private Localizacion localizacion;

//    @OneToOne
//    @JoinColumn(name = "idEntidadPrestadora")
//    private EntidadPrestadora entidadPrestadora; // TODO esto esta mal
//
//    @OneToOne
//    @JoinColumn(name = "idOrganismoDeControl")
//    private OrganismoDeControl organismoDeControl;// TODO esto esta mal

    @OneToMany(mappedBy = "entidadReportadora")
    private List<Incidente> incidentes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "posisionesRankeadas",
            joinColumns = @JoinColumn(name="entidad_id"),
            inverseJoinColumns=@JoinColumn(name="ranking_id"))
    public List<RankTemplateMethod> rankings = new ArrayList<>();

    public Integer cantidadIncidentesSemanales(LocalDate fecha) {
        List<Incidente> incidentesValidos = this.incidentes.stream().filter(i-> i.entraEnCalculoSemanal(fecha)).collect(Collectors.toList());
        Map<Monitoreable, Incidente> resultado = incidentesValidos.stream()
                .collect(Collectors.toMap(
                        Incidente::getServicioAfectado,
                        objeto -> objeto,
                        (obj1, obj2) -> obj1 // Resolver conflictos manteniendo el primer objeto
                ));

        return new ArrayList<Incidente>(resultado.values()).size();

    }


}
