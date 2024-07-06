package vdg.model.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGateway {
	
	private static String user = "pp2proyectoviolenciagenero@gmail.com";
	private static String pass = "kdfa oxdq kvse sziu";
	
	public static boolean enviarMail(String email, String mensaje, String asunto) {

		try {

			// Propiedades de la conexion
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", user);
			props.setProperty("mail.smtp.auth", "true");

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// Reemplazar mi email por el de la persona destinataria
			message.addRecipients(Message.RecipientType.BCC, email);

			message.setSubject(asunto);
			message.setText(mensaje);

			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(user, pass);
			t.sendMessage(message, message.getAllRecipients());

			// Cierre.
			t.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
