package com.gujerbit.battle_cat_web.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.util.Hashing;
import com.gujerbit.battle_cat_web.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private Hashing hashing;
	
	@Value("${jwt.key}")
	private String key;
	
	@Override
	public String createToken(UserVO vo) {
		byte[] hashingKey = hashing.hashing(key.getBytes()).getBytes();
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam(Header.TYPE, Header.JWT_TYPE); //jwt_type
		builder.setIssuer("nyanko-db.shop"); //발급자
		builder.setAudience(vo.getName()); //대상자
		builder.setSubject("token"); //token title
		builder.claim("user", vo); //none-registration claim
		builder.signWith(SignatureAlgorithm.HS256, hashingKey);
		builder.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
		
		return builder.compact();
	}
	
	@Override
	public boolean checkToken(String token) {
		try {
			byte[] hashingKey = hashing.hashing(key.getBytes()).getBytes();
			Jwts.parser().setSigningKey(hashingKey).parseClaimsJws(token).getBody();
			
			getToken(token);
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public Map<String, Object> getToken(String token) {
		byte[] hashingKey = hashing.hashing(key.getBytes()).getBytes();
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(hashingKey).parseClaimsJws(token);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		System.out.println(claims.getBody());
		
		return claims.getBody();
	}
	
}
