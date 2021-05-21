package com.green.jdevd010.CoffeeMintClient.helpers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailHelper {

	
	public static void sendEmail(JavaMailSender mailSender, String from, String to, String subject, String message) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		
		mailSender.send(mailMessage);
	}
	
	public static void sendHTMLEmail(JavaMailSender mailSender, String from, String to, String subject, String message) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		try {
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText("<h1>COFFEEMINT</h1><BR>" + message);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
