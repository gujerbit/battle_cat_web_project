package com.gujerbit.battle_cat_web.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RSA {
	
	@Value("${server.ssl.key-store-password}")
	private String password;
	
	@Autowired
	private Converter converter;
	
	public String encryptRSA(String value) {
		try {
			PublicKey publicKey = getPublicKey();
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encrypt = cipher.doFinal(value.getBytes());
			
			return converter.byteToHex(encrypt);
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public String decryptRSA(String value) {
		try {
			PrivateKey privateKey = getPrivateKey();
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decrypt = cipher.doFinal(converter.hexToByte(value));
			
			return new String(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	private KeyStore getKeyStore() {
		try {
			KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream fis = new FileInputStream("src/main/resources/keystore.p12");
			store.load(fis, password.toCharArray());
			fis.close();
			
			return store;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	private PublicKey getPublicKey() {
		try {
			Certificate certificate = getKeyStore().getCertificate("airpageserver");
			
			return certificate.getPublicKey();
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) getKeyStore().getKey("airpageserver", password.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}

}
