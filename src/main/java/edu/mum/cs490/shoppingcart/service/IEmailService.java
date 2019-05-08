package edu.mum.cs490.shoppingcart.service;
/**
 * Created by Thomas Tibebu,
 * Date April 20, 2019
 **/
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

public interface IEmailService {

	void sendEmail()throws AddressException, MessagingException, IOException;
	void sendEmail(String email, String token)throws AddressException, MessagingException, IOException;
	void sendEmail(String email)throws AddressException, MessagingException, IOException;
	int sendEmail(String emailAddresses, String subject, String body)throws AddressException, MessagingException, IOException;
	void sendEmail(String[] email)throws AddressException, MessagingException, IOException;
	Properties emailProperties();
	Session sessionEmailAuth();
}
