package edu.mum.cs490.shoppingcart.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import edu.mum.cs490.shoppingcart.service.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

	public int sendEmailI(int i)
	{
		return 1;
	}
	
	@Override
	public void sendEmail() throws AddressException, MessagingException, IOException {

		Address thomas = new InternetAddress("ttibebu@mum.edu");
		InternetAddress store = new InternetAddress("pmshoppingcart2019@gmail.com");

		Address[] emailAddress = { thomas, store };
		Message msg = message(emailAddress);
		Transport.send(msg);
	}

	@Override
	public void sendEmail(String emailAddresses, String token) throws AddressException, MessagingException {
		Address emailAddress = new InternetAddress(emailAddresses);// {stanley, store};
		Message msg = message(emailAddress, token);
		Transport.send(msg);
	}
	
	@Override
	public void sendEmail(String emailAddresses) throws AddressException, MessagingException {
		Address emailAddress = new InternetAddress(emailAddresses);// {stanley, store};
		Message msg = message(emailAddress);
		Transport.send(msg);
	}
	
	@Override
	public int sendEmail(String emailAddresses, String subject, String body) throws AddressException, MessagingException {
		int status = -1;
		Address emailAddress = new InternetAddress(emailAddresses);// {stanley, store};
		Message msg = message(emailAddress, subject, body);
		Transport.send(msg);
		status = 1;
		return status;
	}

	@Override
	public void sendEmail(String[] emailAddresses) throws AddressException, MessagingException {

		Address[] addresses = new Address[emailAddresses.length];// {stanley, store};
		for (int i = 0; i < emailAddresses.length; i++) {
			addresses[i] = new InternetAddress(emailAddresses[i]);
		}
		Message msg = message(addresses);

		Transport.send(msg);
	}

	@Override
	public Properties emailProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}

	@Override
	public Session sessionEmailAuth() {
		Properties props = emailProperties();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pmshoppingcart2019@gmail.com", "shoppingcart2468");
			}
		});
		return session;
	}

	private Message message(Address emailAddress, String token) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("pmshoppingcart2019@gmail.com", false));

		msg.setRecipient(Message.RecipientType.TO, emailAddress);

		msg.setSubject("Test email");
		msg.setContent("This is just to test the email module.", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("This is our test email. Token code: " + token, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}
	
	private Message message(Address emailAddress) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("pmshoppingcart2019@gmail.com", false));

		msg.setRecipient(Message.RecipientType.TO, emailAddress);

		msg.setSubject("Test email");
		msg.setContent("This is just to test the email module.", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("This is from our test email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}
	
	private Message message(Address emailAddress, String subject, String body) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("pmshoppingcart2019@gmail.com", false));

		msg.setRecipient(Message.RecipientType.TO, emailAddress);

		msg.setSubject(subject);
		msg.setContent("Email module(customize).", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}

	private Message message(Address[] emailAddress) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("pmshoppingcart2019@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, emailAddress);

		msg.setSubject("Test email");
		msg.setContent("This is just to test the email module.", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("This is from our test email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}

}
