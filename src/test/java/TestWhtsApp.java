import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.WhatsAppSender;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestWhtsApp {

    @Test
    public void enviarMensaje() throws IOException, UnirestException {
        Mensaje mensaje = new Mensaje("+541138157280", "hola desde tp");
        WhatsAppSender mensajero = new WhatsAppSender();
        mensajero.enviarMensaje(mensaje);
    }
}
