package model.entities.comunidad;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Getter @Setter
@Entity
public class Usuario extends EntidadPersistente {


    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    private TipoMiembro tipoMiembro;

    @Column
    private String mail;

    @Column
    private String password;



    public Usuario(Rol rol, TipoMiembro tipoMiembro, String mail, String password) {
        this.rol = rol;
        this.tipoMiembro = tipoMiembro;
        this.mail = mail;
        definirContrasenia(password);
    }

    public Usuario() {

    }


    public void definirContrasenia(String clave){
        this.password = this.hashearContrasenia(clave);
      //  this.fechaDeDefinicionContrasenia = new Date(System.currentTimeMillis()); // deberia persistirse una vez validada desnormalizado TODO
    }


    public static String hashearContrasenia(String contrasenia) {
        String sha256hex = Hashing.sha256()
                .hashString(contrasenia, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}