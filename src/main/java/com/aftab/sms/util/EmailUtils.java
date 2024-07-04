package com.aftab.sms.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtils {
	@Autowired
private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String subject,String body,String to,File f) {
		
		try {
			
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();	
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		helper.setSubject(subject);
		helper.setText(body,true);
		helper.setTo(to);
		helper.addAttachment(f.getName(),f);
		javaMailSender.send(mimeMessage);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return true;
	}
}
