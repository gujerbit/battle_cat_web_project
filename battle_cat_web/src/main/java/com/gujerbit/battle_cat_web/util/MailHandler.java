package com.gujerbit.battle_cat_web.util;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	
	private JavaMailSender sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public MailHandler(JavaMailSender sender) throws MessagingException {
		this.sender = sender;
		message = sender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}
	//보내는 사람 이메일
	public void setFrom(String email) throws MessagingException {
		messageHelper.setFrom(email);
	}
	//받는 사람 이메일
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}
	//subject
	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}
	//content
	public void setText(String text) throws MessagingException {
		messageHelper.setText(text, true);
	}
	//첨부 파일
	public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToAttachment).getFile();
		FileSystemResource fsr = new FileSystemResource(file);
		messageHelper.addAttachment(displayFileName, fsr);
	}
	//이미지 삽입
	public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToInline).getFile();
		FileSystemResource fsr = new FileSystemResource(file);
		messageHelper.addInline(contentId, fsr);
	}
	//send
	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
