/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosExternos;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author calde
 */
public class EnvioCorreoElectronico {
    private static final String correoEmisor = "bancojfs@outlook.es";
    
    public static boolean enviarCorreo(String pCorreoDestinatario, String pAsunto, String pMensaje) {
        try {
            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", "smtp.office365.com");
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", "587");
            propiedad.setProperty("mail.smtp.auth", "true");
            Session sesion = Session.getDefaultInstance(propiedad);

            String contrasenaCorreo = "proyecto1_DS";
            BodyPart segmentoDeTextoMensaje = new MimeBodyPart();
            segmentoDeTextoMensaje.setContent(pMensaje, "text/html");
            MimeMultipart bloqueDeTexto = new MimeMultipart();
            bloqueDeTexto.addBodyPart(segmentoDeTextoMensaje);
            MimeMessage message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(correoEmisor)); 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(pCorreoDestinatario));
            message.setSubject(pAsunto);
            message.setContent(bloqueDeTexto);
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEmisor, contrasenaCorreo);
            transportar.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transportar.close();
            return true;
        }
        catch(MessagingException e){
            return false;
        }
    }
}
