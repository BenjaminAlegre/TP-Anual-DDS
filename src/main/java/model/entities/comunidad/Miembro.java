package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;
import model.entities.entidades.Entidad;
import model.entities.entidades.EntidadPrestadora;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.*;
import model.entities.persistencia.EntidadPersistente;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Miembro  extends EntidadPersistente implements Reportador, Observador {


    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column
    private String mail;

    @Enumerated(EnumType.STRING)
    private TipoMiembro tipo;

    @ManyToOne
    @JoinColumn(name = "deptoId")
    private Localizacion localizacion;

    @ManyToMany(mappedBy = "miembros")
    private List<Monitoreable> moritoreable = new ArrayList<>();

    @ManyToMany(mappedBy = "miembros")
    private List<Comunidad> comunidades = new ArrayList<>();;

    @Enumerated(EnumType.STRING)
    private MedioNotificacion medioNotificacion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "horarios", joinColumns = @JoinColumn(name = "prestador_id"))
    private List<String> horariosDeNotificacion = new ArrayList<>();

    @ManyToMany(mappedBy = "suscriptores")
    private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras = new ArrayList<>();; //podria ser un Set<EntidadPrestadora>


    public Miembro(String nombre, String apellido, String mail){
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;

    }

    public Miembro() {

    }


    public void asociarseAComunidad(Comunidad comunidad) {
        comunidades.add(comunidad);
    }

    public void desasociarseDeComunidad(Comunidad comunidad) {
        comunidades.remove(comunidad);
    }

    @Override
    public Incidente generarIncidente() {
        Incidente incidente = new Incidente();//TODO
        incidente.setEstado(EstadoIncidente.ACTIVO);
        incidente.setReportador(this.getId().toString());
        incidente.setHorarioApertura(null);
        incidente.setObservaciones(null);
        incidente.setServicioAfectado(null);
        return incidente;
    }

    @Override
    public Incidente generarIncidente(Monitoreable servicioAfectado, String observaciones, EstadoIncidente estado, LocalDate horarioApertura, LocalDate horarioCierre, String idReportador, Entidad entidadAfectada) {
        return null;
    }

    @Override
    public void cerrarIncidente(Incidente incidente) {
        incidente.setEstado(EstadoIncidente.CERRADO);
    }

    public void suscribirseAEntidad(EntidadPrestadora entidadPrestadora) {
        suscripcionesAEntidadesPrestadoras.add(entidadPrestadora);
    }



    @Override
    public void serNotificadoPor(Observable observable) { // usar case es ma extensible y declarativo, lo mejor seria objetos pero como solo son dos medios, case
// hay que ver que se manda el observable
        switch (this.medioNotificacion){
            case WHATSAPP: break;
            case CORREO_ELECTRONICO: break;
        }
//        if(this.medioNotificacion == MedioNotificacion.WHATSAPP) {
//            //TODO
//        }
//        else if(this.medioNotificacion == MedioNotificacion.CORREO_ELECTRONICO) {
//            //TODO
//        }
    }



}
