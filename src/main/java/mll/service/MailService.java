package mll.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mll.beans.Invite;
import mll.utility.Configuration;

public class MailService
{
	Configuration conf = new Configuration();
	
	/**
	* This method takes Invite object as an input and send 
	* the invite as an email to the recipient. 
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	public Boolean sendInvite(Invite invite)
	{
		Boolean isMailSent = false;
		
		try
		{
			sendMail(invite.getToken().getEmailId(), "Invite from Media Licencing Lab", "\n"+ invite.getToken().getMessageBody() + "\n\nHere is your personalized invitation link. Use it to create your profile in the platform :: " + invite.getUrl() + "\n\nIf you received this message in error, or if you have a problem during the registration process, please contact "+ conf.EMAIL_ADDRESS_FOR_SUPPORT +".\n\nThanks,\nMedia Team");
		}
		catch(Exception e)
		{
			isMailSent = false;
		}
		
		return isMailSent;
	}
	
	/**
	* This method takes receiverMail, subjectLine, message as input 
	* and sends the mail to the recipient. 
	* @author  Dhaval Patel
	* @version 1.0
	* @since   2016-04-06 
	*/
	public void sendMail(String receiverMail, String subjectLine, String msg) 
	{
		try 
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
	
			Session session = Session.getInstance(props, new javax.mail.Authenticator() 
			{
				@Override
				protected PasswordAuthentication getPasswordAuthentication() 
				{
					return new PasswordAuthentication(conf.USER_NAME_FOR_EMAIL, conf.PASSWOD_FOR_EMAIL);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(conf.FROM_EMAIL_ADDRESS, conf.FROM_NAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverMail));
			message.setSubject(subjectLine);
			message.setText(msg);

			Transport.send(message);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}