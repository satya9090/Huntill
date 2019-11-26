package com.yotabytes.huntill.talentpool.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.research.ws.wadl.Request;
import com.yotabytes.huntill.talentpool.domain.CandidateInformation;

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
	public static void mailSendUtil(HttpSession session,HttpServletRequest request)
	{
		CandidateInformation information=(CandidateInformation)session.getAttribute("information");
		MailUtil.sendToCandidateMail("", information.getEmailId(), "",
				"TalentPool - Registration",
				"Hello,<br/><br/> Registration sucessfully <br/>"
				+"<html><body><p>"+"<a href=http://"
				+request.getServerName()+":"
				+request.getServerPort()
				+"/"+"api"+"/"
				+"VerifyEmail"+"?"
				+"uniqeId"+"="+information.getCandidateUniqeId()
				+">VerifyEmail</a></body></html>"
				+  "<br/-----<br/><br/>Thank you");

		MailUtil.sendMail("", "1994satyabrataw@gmail.com",
				"", "",
				"Candidate- Registration",
				"Hello,<br/><br/> New coustomer registration ,<br/><br/>"
				+ "<table border=1>"
				+ "<th>Name</th>"
				+ "<th>Email-Id</th>"
				+ "<th>Contact-no</th>"
						+ "<tr>" + "<td>" + information.getFirst_name()+information.getMiddle_name()+""+information.getLast_name() 
						+ "</td>"  + "<td>" + information.getEmailId() 
						+ "</td>" + "<td>"+ information.getContact_number()
						+ "</td>"+ "</tr>"  + "</table>" 
						+ "</b><br/><br/>Message: <b>"
						+ "</b><br/><br/-----<br/><br/>"
						+ "This e-mail was sent from  - Yotabytes PVT LTD. ");
	}
	
	public static void mailSendToResetPassword(HttpSession session,HttpServletRequest request)
	{
		CandidateInformation information=(CandidateInformation)session.getAttribute("information");
		session.setAttribute("candidate_uniqueId", information.getCandidateUniqeId());
		MailUtil.sendToCandidateMail("", information.getEmailId(), "",
				"TalentPool - ResetPassword",
				"Hello,<br/><br/> Reset your Password  <br/>"
				+"<html><body><p>"+"<a href=http://"
				+request.getServerName()+":"
				+request.getServerPort()
				+"/"+"api"+"/"
				+"ResetPasswordPage"
				+">ResetPassword</a></body></html>"
				+  "<br/-----<br/><br/>Thank you");
	
}
}
