package model.entities.notificacion.envioNotificacion.WhatsAppSender;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Getter;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.ResponseMensaje;
import model.entities.notificacion.envioNotificacion.WhatsAppSender.adapters.WassengerApi;

import java.io.IOException;

@Getter
public class WhatsAppSender {
    private static WhatsAppSender instancia = null;
    private WassengerApi adapter = new WassengerApi();

    public WhatsAppSender() throws IOException {

    }


//
//    public static WhatsAppSender instancia(){
//        if(instancia== null){
//            instancia = new WhatsAppSender();
//        }
//        return instancia;
//    }

    public void enviarMensaje(Mensaje mensaje) throws IOException, UnirestException {
        ResponseMensaje response= this.adapter.enviarMensaje(mensaje);
        if (!response.getStatus().equals("400") ){
        System.out.println("Se envio correctamente el  mensaje:   "+mensaje.getMensaje());
        System.out.println("Al  numero");
        System.out.println(response.getPhone());
        System.out.println("A la hora ");
        System.out.println(response.getDeliverAt());}
        else {
            System.out.println("No se pudo enviar el mensaje");

        }

    }


}
