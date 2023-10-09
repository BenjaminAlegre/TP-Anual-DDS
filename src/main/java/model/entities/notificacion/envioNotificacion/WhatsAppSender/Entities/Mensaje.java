package model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mensaje {
    String destinatario;
    String mensaje;


    public Mensaje (){
    }
    public Mensaje (String destino,String mensajeTexto){
        this.destinatario = destino;
        this.mensaje=mensajeTexto;
    }
}
