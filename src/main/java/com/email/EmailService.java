package com.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	public synchronized void sendEmail(String searchUrl , String customSearchQuery){
		
		  final String to = "christopher.j.urbanec@jpmorgan.com";
	      final String from = "urbo143851@gmail.com";
	      final String host = "smtp.gmail.com";
	      final String username = "urbo143851";
	      final String password = "Spring87@@";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      Session session = Session.getInstance(props,
	      
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         	}
	      	});

	      try {
	        
	         Message message = new MimeMessage(session);

	         message.setFrom(new InternetAddress(from));

	         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

	         message.setSubject("Testing Subject");

	         message.setText("searchUrl : " + searchUrl + " | customSearchQuery : " + customSearchQuery + " -sent email using JavaMailAPI and Google SMTP Server");

	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}
