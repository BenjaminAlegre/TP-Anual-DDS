package model.entities.comunidad;


import model.entities.entidades.EntidadPrestadora;
import model.entities.incidentes.Incidente;
import model.entities.incidentes.Reportador;
import model.entities.localizacion.Localizacion;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Miembro implements Reportador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMiembro;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column
    private String mail;

    @ManyToOne
    @JoinColumn(name = "idLocalizacion", referencedColumnName = "idLocalizacion")
    private List<Localizacion> localizacion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Monitoreable> moritoreable;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comunidad> comunidades;
    @Enumerated(EnumType.STRING)
    private MedioNotificacion medioNotificacion;

    @Column
    private List<String> horariosDeNotificacion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//idem set
    private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras; //podria ser un Set<EntidadPrestadora>


    public Miembro(String nombre, String apellido, String mail, List<Localizacion> localizacion, List<Monitoreable> moritoreable, List<Comunidad> comunidades, MedioNotificacion medioNotificacion, List<String> horariosDeNotificacion, List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.localizacion = new ArrayList<>();
        this.moritoreable = moritoreable;
        this.comunidades = new ArrayList<>();
        this.medioNotificacion = medioNotificacion;
        this.horariosDeNotificacion = new ArrayList<>();
        this.suscripcionesAEntidadesPrestadoras = suscripcionesAEntidadesPrestadoras;
    }

    //TODO validar que el incidente pertenezca al miembro
    public void cerrarIncidente(Incidente incidente){
        incidente.cerrarIncidente();
    }


    public Integer getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Localizacion> getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(List<Localizacion> localizacion) {
        this.localizacion = localizacion;
    }

    public List<Monitoreable> getMoritoreable() {
        return moritoreable;
    }

    public void setMoritoreable(List<Monitoreable> moritoreable) {
        this.moritoreable = moritoreable;
    }

    public List<Comunidad> getComunidades() {
        return comunidades;
    }

    public void setComunidades(List<Comunidad> comunidades) {
        this.comunidades = comunidades;
    }

    public MedioNotificacion getMedioNotificacion() {
        return medioNotificacion;
    }

    public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
        this.medioNotificacion = medioNotificacion;
    }

    public List<String> getHorariosDeNotificacion() {
        return horariosDeNotificacion;
    }

    public void setHorariosDeNotificacion(List<String> horariosDeNotificacion) {
        this.horariosDeNotificacion = horariosDeNotificacion;
    }

    public List<EntidadPrestadora> getSuscripcionesAEntidadesPrestadoras() {
        return suscripcionesAEntidadesPrestadoras;
    }

    public void setSuscripcionesAEntidadesPrestadoras(List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras) {
        this.suscripcionesAEntidadesPrestadoras = suscripcionesAEntidadesPrestadoras;
    }
}
