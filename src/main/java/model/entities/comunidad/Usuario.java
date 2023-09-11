package model.entities.comunidad;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Integer idUsuario;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    private TipoMiembro tipoMiembro;

    @Column
    private String mail;


    public TipoMiembro getTipoMiembro() {
        return tipoMiembro;
    }

    public void setTipoMiembro(TipoMiembro tipoMiembro) {
        this.tipoMiembro = tipoMiembro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
