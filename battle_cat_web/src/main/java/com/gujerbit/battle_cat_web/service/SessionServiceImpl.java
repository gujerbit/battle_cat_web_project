package com.gujerbit.battle_cat_web.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SessionServiceImpl implements SessionService {

	private String salt = "nyanko";
	
	@Override
	public String createToken(UserVO vo) {
		JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ", "JWT");
		builder.setSubject("token").claim("user", vo);
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		return builder.compact();
	}
	
	@Override
	public void checkToken(String token) {
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token);
	}
	
	@Override
	public Map<String, Object> getToken(String token) {
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return claims.getBody();
	}
	
}
