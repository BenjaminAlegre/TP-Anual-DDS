package model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mensaje {
    String numeroTelefono;
    String mensaje;

    public Mensaje (){
    }
    public Mensaje (String numero,String mensajeTexto){
        this.numeroTelefono=numero;
        this.mensaje=mensajeTexto;
    }
}
