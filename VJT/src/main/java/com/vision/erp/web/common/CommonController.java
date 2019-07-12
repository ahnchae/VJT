package com.vision.erp.web.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.service.domain.Email;

@RestController
public class CommonController {
	
	//Sample : {"recipient":"qhdqhdekd261@gmail.com","subject":"���� �Գ�?","contents":"�������ϴ� ô �����"}
	@RequestMapping(value = "/common/sendEmail", method = RequestMethod.POST)
	public void sendEmail(@RequestBody Email email) throws Exception{
		System.out.println("mailService");
		
		String host = "smtp.naver.com";
		
		final String username = "glossator@naver.com";
		final String password = "";
		int port = 465;
		
		//���ϳ���
		String recipient = email.getRecipient();
		String subject = email.getSubject();
		String body = email.getContents();
		
		
		Properties props = System.getProperties();
		
		//SMTP �������� ����
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.trust", host);
		
		//Session ����
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		String un=username;
		String pw=password;
		protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
			return new javax.mail.PasswordAuthentication(un, pw);
		}
		});
		session.setDebug(true);
		
		
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress("glossator@naver.com"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		    mimeMessage.setSubject(subject);
		    mimeMessage.setText(body);
		   
		    // ������ �߽��Ѵ�
		    Transport.send(mimeMessage);
		} catch (Exception e) {
		   	e.printStackTrace();
		}
	}
	

}
