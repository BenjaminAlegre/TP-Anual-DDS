package model.entities.notificacion;

import com.mashape.unirest.http.exceptions.UnirestException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface Observador {


    void serNotificadoPor(Observable observable, String mensaje) throws IOException, MessagingException, UnirestException;
}
