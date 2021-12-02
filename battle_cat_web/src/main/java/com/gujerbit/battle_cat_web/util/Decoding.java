package com.gujerbit.battle_cat_web.util;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.stereotype.Component;

@Component
public class Decoding {

	public byte[] base64Decoding(byte[] value) {
		Decoder decoder = Base64.getDecoder();
		
		return decoder.decode(value);
	}
	
}
