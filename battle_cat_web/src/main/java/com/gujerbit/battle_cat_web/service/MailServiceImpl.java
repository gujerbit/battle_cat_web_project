package com.gujerbit.battle_cat_web.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.util.MailHandler;

@Service
public class MailServiceImpl implements MailService {

//	@Autowired
//	private TOTP totp;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public String mailSend(String email, String text, String title) {
		String registerCode = createRandomCode();
		
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			String htmlContent = "<p>" + text + ": <span style=\"color: #ffc038\">" + registerCode + "</span></p> <p>해당 코드를 10분 안에 코드 입력 부분에 입력하세요</p>";
			mailHandler.setTo(email);
			mailHandler.setFrom("nyanko.db@gmail.com");
			mailHandler.setSubject(title);
			mailHandler.setText(htmlContent);
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return registerCode;
	}
	
	private String createRandomCode() {
		String result = "";
		
		for(int i = 0; i < 6; i++) {
			Random rnd = new Random();
			int rndNum = rnd.nextInt(3);
			
			if(rndNum == 0) {
				result += String.valueOf((char)(rnd.nextInt(26) + 97));
			} else if(rndNum == 1) {
				result += String.valueOf((char)(rnd.nextInt(26) + 65));
			} else {
				result += String.valueOf(rnd.nextInt(10));
			}
		}
		
		return result;
	}
	
}
