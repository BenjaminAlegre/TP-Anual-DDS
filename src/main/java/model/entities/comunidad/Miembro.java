package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;
//import model.entities.entidades.EntidadPrestadora;
import model.entities.entidades.EntidadPrestadora;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.*;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Miembro implements Reportador, Observador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private List<Monitoreable> moritoreable;

    @ManyToMany(mappedBy = "miembros")
    private List<Comunidad> comunidades;

    @Enumerated(EnumType.STRING)
    private MedioNotificacion medioNotificacion;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "horarios", joinColumns = @JoinColumn(name = "prestador_id"))
    private List<String> horariosDeNotificacion = new ArrayList<>();

    @ManyToMany(mappedBy = "suscriptores")
    private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras; //podria ser un Set<EntidadPrestadora>


    public Miembro(String nombre, String apellido, String mail, List<Localizacion> localizacion, List<Monitoreable> moritoreable, List<Comunidad> comunidades, MedioNotificacion medioNotificacion, List<String> horariosDeNotificacion, List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        //this.localizacion = new ArrayList<>();
        this.moritoreable = moritoreable;
        this.comunidades = new ArrayList<>();
        this.medioNotificacion = medioNotificacion;
        this.horariosDeNotificacion = new ArrayList<>();
        this.suscripcionesAEntidadesPrestadoras = suscripcionesAEntidadesPrestadoras;
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
    public Incidente generarIncidente(Incidente incidente) {
        incidente.setEstado(EstadoIncidente.ACTIVO);
        return incidente;
    }

    @Override
    public void cerrarIncidente(Incidente incidente) {
        incidente.setEstado(EstadoIncidente.CERRADO);
    }

    public void suscribirseAEntidad(EntidadPrestadora entidadPrestadora) {
        suscripcionesAEntidadesPrestadoras.add(entidadPrestadora);
    }



    @Override
    public void serNotificadoPor(Observable observable) {
        //TODO recibe la notificacion
    }
}
