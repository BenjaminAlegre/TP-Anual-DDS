package model.entities.notificacion;


import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import lombok.Setter;
import model.entities.comunidad.Miembro;
import model.entities.notificacion.envioNotificacion.EstrategiaNotificacion;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;

import javax.mail.MessagingException;
import java.io.IOException;

@Getter @Setter
public class Notificador {

    public EstrategiaNotificacion estrategiaNotificacion;

    public void notificar( Mensaje mensaje) throws UnirestException, IOException, MessagingException {
        this.estrategiaNotificacion.enviarMensaje(mensaje);
    }


}
