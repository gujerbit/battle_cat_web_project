package com.gujerbit.battle_cat_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.gujerbit.battle_cat_web.util.MailHandler;
import com.gujerbit.battle_cat_web.util.TOTP;

public class MailServiceImpl implements MailService {

	@Autowired
	private TOTP totp;
	
	private JavaMailSender mailSender;
	
	@Override
	public void mailSend(String email) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			String htmlContent = "<p>회원가입 코드: <span style=\"color: #ffc038\">" + totp.setOTP(600, "6") + "</span></p> <p>해당 코드를 10분 안에 코드 입력 부분에 입력하세요</p>";
			mailHandler.setTo(email);
			mailHandler.setFrom("nyanko.db@gmail.com");
			mailHandler.setSubject("냥코대전쟁 정보 & 커뮤니티 사이트 회원가입 코드");
			mailHandler.setText(htmlContent);
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
