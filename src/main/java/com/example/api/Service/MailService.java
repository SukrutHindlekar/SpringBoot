package com.example.api.Service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	static final String FROM = "sukruthindlekar786@gmail.com";	
	static final String FROMNAME = "Sukrut";
	static final String SMTP_USERNAME = "sukruthindlekar786@gmail.com";
	static final String SMTP_PASSWORD = "dracula45547";
	static final String HOST = "smtp.gmail.com";
	static final String PORT = "587";
	static final String SUBJECT = "Reset Password OTP";
	
	public boolean sendEmail(String emailId, int otpValue)  {
		
		try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
            msg.setSubject(SUBJECT);
            msg.setContent("Please enter otp to verify email:" + otpValue, "text/html");

            Transport transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }		
	}		
}
