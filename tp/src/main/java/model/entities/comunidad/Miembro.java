package model.entities.comunidad;


import model.entities.entidades.EntidadPrestadora;
import model.entities.incidentes.Incidente;
import model.entities.incidentes.Reportador;
import model.entities.localizacion.Localizacion;
import model.entities.servicio.Monitoreable;

import java.util.ArrayList;
import java.util.List;

public class Miembro implements Reportador {
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private List<Localizacion> localizacion;
    private List<Monitoreable> moritoreable;
    private List<Comunidad> comunidades;
    private MedioNotificacion medioNotificacion;
    private List<String> horariosDeNotificacion; //idem set
    private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras; //podria ser un Set<EntidadPrestadora>



    public Miembro(String nombre, String apellido, String mail, List<Localizacion> localizacion, List<Monitoreable> moritoreable,  List<Comunidad> comunidades, MedioNotificacion medioNotificacion, List<String> horariosDeNotificacion, List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras) {
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


}
