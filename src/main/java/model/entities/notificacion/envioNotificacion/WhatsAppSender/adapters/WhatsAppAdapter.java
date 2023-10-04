package model.entities.notificacion.envioNotificacion.WhatsAppSender.adapters;

import com.mashape.unirest.http.exceptions.UnirestException;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.ResponseMensaje;

import java.io.IOException;

public interface WhatsAppAdapter {

    public ResponseMensaje enviarMensaje(Mensaje mensaje) throws IOException, UnirestException;
}
