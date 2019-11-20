package com.yotabytes.huntill.talentpool.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	
	public static void sendMail(String dummyMailAddress, final String toaddress,final String ccaddress, final String bccaddress,
			final String mailSubject, final String mailBody) {
		
		new Thread(new Runnable() {  
			public void run() {
				
				/*
				 * Below email address and password needs to be provided by client
				 * Below mail from which mail will send
				 * */
				final String username = "noreply.osdma@gmail.com";//provide correct mail address
		        final String password = "noreply@1";//provide password
		        
				Properties props = new Properties();
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.connectiontimeout", "3000"); 
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");


				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username,
										password);
							}
						});
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(toaddress));
					message.setRecipients(Message.RecipientType.CC,
							InternetAddress.parse(ccaddress));
					message.setRecipients(Message.RecipientType.BCC,
							InternetAddress.parse(bccaddress));
					message.setSubject(mailSubject);
					message.setContent(mailBody, "text/html");

					Transport.send(message);

					System.out.println("Email sent to :" + toaddress
							+ " ," + ccaddress+"," + bccaddress+", successfully......");
					
					Transport transport = session.getTransport("smtp");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();

	}
	public static void sendToCandidateMail(String dummyMailAddress, final String toaddress,final String bccaddress,
			final String mailSubject, final String mailBody) {
		
		new Thread(new Runnable() {
			public void run() {
				
				/*
				 * Below email address and password needs to be provided by client
				 * Below mail from which mail will send
				 * */
				final String username = "noreply.osdma@gmail.com";//provide correct mail address
		        final String password = "noreply@1";//provide password
		        
				Properties props = new Properties();
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.connectiontimeout", "3000");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");


				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username,
										password);
							}
						});
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(toaddress));
					message.setRecipients(Message.RecipientType.BCC,
							InternetAddress.parse(bccaddress));
					message.setSubject(mailSubject);
					message.setContent(mailBody, "text/html");

					Transport.send(message);

					
					System.out.println("Email sent to :" + toaddress
							+ " ," + bccaddress+", successfully......");
					
					Transport transport = session.getTransport("smtp");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();

	}
}
