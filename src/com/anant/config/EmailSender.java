package com.anant.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	private final String EMAIL = "admin@algorhythm.co.in";
	private final String PASSWORD = "algo#1234";

	public boolean sendEmail(String receipent, String subject, String body) {
		try {
			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(EMAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Transport.send(message);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Session getSession() {
		final javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL, PASSWORD);
			}
		};
		return Session.getInstance(setProps(), authenticator);
	}

	private Properties setProps() {
		Properties properties = new Properties();
		properties.put("mail.smtp.user", EMAIL);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 25);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.ssl.enable", false);
		return properties;
	}
}
