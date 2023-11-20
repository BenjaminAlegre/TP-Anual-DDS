package model.entities.notificacion.envioNotificacion;
import com.mashape.unirest.http.exceptions.UnirestException;

import model.entities.notificacion.envioNotificacion.WhatsAppSender.Entities.Mensaje;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailSender extends EstrategiaNotificacion{

        Properties properties = new Properties();
        Session session;

        public EmailSender() throws IOException{
                InputStream lectura= new FileInputStream("src\\main\\resources\\configuracion.properties");
                properties.load(lectura);
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                properties.put("mail.smtp.ssl.trust", "*");


                session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
                                }
                        });
        }




        @Override
        public void enviarMensaje(Mensaje mensaje) throws IOException, UnirestException, MessagingException {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(properties.getProperty("username")));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mensaje.getDestinatario())); // Recipient's email address
                message.setSubject("Notificacion Incidente");
                message.setText(mensaje.getMensaje());
                session.setDebug(true);

                Transport.send(message);

                System.out.println("Email sent successfully!");

        }
}

