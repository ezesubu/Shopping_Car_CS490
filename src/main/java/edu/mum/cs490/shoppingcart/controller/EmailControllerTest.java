package edu.mum.cs490.shoppingcart.controller;

import edu.mum.cs490.shoppingcart.service.impl.EmailServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.Arrays;


@RestController
public class EmailControllerTest {

	@GetMapping("/email")
	public String sendEmail() throws AddressException, MessagingException, IOException {

		EmailServiceImpl email = new EmailServiceImpl();
		//email.sendEmail();
		String thomas = "ttibebu@mum.edu";
		String store = "tomnetgeneraltradingplc@gmail.com";
		String[] emails = {thomas, store};
		email.sendEmail(emails);
		return "email send successfully to "+Arrays.toString(emails)+"";//"email send successfully to "+ thomas+"";
	}
	
	@GetMapping("/emailparam")
	public String sendEmailParam() throws AddressException, MessagingException, IOException {

		EmailServiceImpl email = new EmailServiceImpl();
		//email.sendEmail();
		String thomas = "ttibebu@mum.edu";
		//String store = "shoppingchart2019@gmail.com";
		//email.sendEmail(thomas);
		//String[] emails = {thomas, store};
		//email.sendEmail(emails);
		email.sendEmail(thomas, "Test admin sending message", "Admin send you a message to reset your password in the system.");
		//return "email send successfully to "+Arrays.toString(emails)+"";//"email send successfully to "+thomas+"";
		return "email send successfully to "+thomas+"";
	}
}
