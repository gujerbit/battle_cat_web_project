package com.gujerbit.battle_cat_web.util;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class TOTP {
	
	private final String KEY = "DFO9JdfcZuC3IscpLfhj3XWlXwn0eI9F1iPkPyMzBEQzr0rmwlTcFYlpyXs457MV";
	private final int[] DIGITS = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
	
	public String setOTP(long time, String length) {
		long T0 = 0;
		long X = time;
		String steps = "0";
		String result = "";
		long sec = System.currentTimeMillis();
		
		try {
			long T = (sec / 1000 - T0) / X;
			steps = Long.toHexString(T).toUpperCase();
			
			while(steps.length() < 16) steps = "0" + steps;
			
			result = generateTOTP(KEY, steps, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String generateTOTP(String key, String time, String length) {
		int codeDigits = Integer.decode(length).intValue();
		byte[] hash = null;
		
		while(time.length() < 16) time = "0" + time;
		
		hash = hmacSHA256Encrypt(hexToArray(key), hexToArray(time));
		int offset = hash[hash.length - 1] & 0xf;
		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | ((hash[offset + 3] & 0xff));
		int otp = binary % DIGITS[codeDigits];
		String result = Integer.toString(otp);
		
		while(result.length() < codeDigits) result = "0" + result;
		
		return result;
	}
	
	private byte[] hmacSHA256Encrypt(byte[] key, byte[] value) {
		try {
			Mac hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec keySpec = new SecretKeySpec(key, "RAW");
			hmac.init(keySpec);
			
			return hmac.doFinal(value);
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}
	
	private byte[] hexToArray(String value) {
		byte[] temp = new BigInteger("10" + value, 16).toByteArray();
		byte[] result = new byte[temp.length - 1];
		
		for(int i = 0; i < result.length; i++) result[i] = temp[i + 1];
		
		return result;
	}

}
