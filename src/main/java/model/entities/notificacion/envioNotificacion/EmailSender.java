package model.entities.notificacion.envioNotificacion;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailSender {


//
//            // Configuración de las propiedades del servidor SMTP
//            Properties properties = new Properties();
//            properties.put("mail.smtp.host", "smtp.example.com"); // Cambia esto al servidor SMTP que estés utilizando
//            properties.put("mail.smtp.port", "587"); // Puerto SMTP (587 es común para TLS)
//            properties.put("mail.smtp.auth", "true"); // Habilita la autenticación
//            properties.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para seguridad (TLS)
//
//            // Credenciales de autenticación
//            String username = "tu_correo@example.com"; // Tu dirección de correo
//            String password = "tu_contraseña"; // Tu contraseña
//
//            // Creación de una sesión de correo
//            Session session = Session.getInstance(properties, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//    Message message = new MimeMessage(session);
//            try {
//
//                // Creación del mensaje
//
//                message.setFrom(new InternetAddress(username)); // Remitente
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destinatario@example.com")); // Destinatario
//                message.setSubject("Asunto del correo");
//                message.setText("Contenido del correo electrónico");
//
//                // Envío del correo
//                Transport.send(message);
//
//                System.out.println("Correo electrónico enviado exitosamente.");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//                System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
//            }
        }

