package model.entities.comunidad;

import lombok.Getter;
import lombok.Setter;
//import model.entities.entidades.EntidadPrestadora;
import model.entities.localizacion.Localizacion;
import model.entities.notificacion.Incidente;
import model.entities.notificacion.Reportador;
import model.entities.servicio.Monitoreable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Miembro implements Reportador {
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
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
    private List<String> horariosDeNotificacion;

 //   @ManyToMany(mappedBy = "suscriptores")
  //  private List<EntidadPrestadora> suscripcionesAEntidadesPrestadoras; //podria ser un Set<EntidadPrestadora>

/*
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


*/

    @Override
    public Incidente generarIncidente() { //TODO
        return null;
    }

    @Override
    public void cerrarIncidente() {

    }


}
