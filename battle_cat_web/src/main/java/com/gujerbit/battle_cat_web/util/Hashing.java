package com.gujerbit.battle_cat_web.util;

import java.security.MessageDigest;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hashing {
	
	@Autowired
	private Converter converter;
	
	public String hashing(byte[] value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			for(int i = 0; i < 77; i++) {
				 String temp = converter.byteToString(value);
				 md.update(temp.getBytes());
				 value = md.digest();
			}
			
			return converter.byteToString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String createSalt() {
		String result = "";
		
		for(int i = 0; i < 16; i++) {
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
		
		return hashing(result.getBytes());
	}

}
