package model.entities.comunidad;

//import com.google.common.hash.Hashing;
import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import model.entities.persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.nio.charset.StandardCharsets;

@Getter @Setter
@Entity
public class Usuario extends EntidadPersistente {


    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column
    private String mail;

    @Column
    private String password;



    public Usuario( String mail, String password, Rol rol) {
        this.mail = mail;
        definirContrasenia(password);
        this.rol = rol;
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