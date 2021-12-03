package com.gujerbit.battle_cat_web.service;

import java.util.HashMap;
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
		builder.setSubject(vo.getGrade().equals("user") ? "user" : vo.getGrade().equals("admin") ? "admin" : vo.getGrade().equals("operator") ? "operator" : "developer"); //token title
		builder.claim("user", vo); //none-registration claim
		builder.signWith(SignatureAlgorithm.HS256, hashingKey);
//		builder.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
		
		return builder.compact();
	}
	
	@Override
	public boolean checkToken(String token, String path) {
		try {
			byte[] hashingKey = hashing.hashing(key.getBytes()).getBytes();
			Jwts.parser().setSigningKey(hashingKey).parseClaimsJws(token).getBody();
			
			Claims claims = getToken(token).getBody();
			
			if(!claims.getIssuer().equals("nyanko-db.shop")) return false;
			
			if(((path.equals("/get_admin_log") || path.equals("/user_reject") || path.equals("/user_forever_reject") || path.equals("/user_reject_release") || path.equals("/user_grade_setting") || path.equals("/set_admin_log"))) && claims.getSubject().equals("user")) return false;
			else if(!claims.getSubject().equals("user") && !claims.getSubject().equals("admin") && !claims.getSubject().equals("operator") && !claims.getSubject().equals("developer")) return false;
			
			String temp = claims.get("user").toString().replaceAll("[{}/\s/]", "");
			String[] arr = temp.split(",");
			Map<String, String> map = new HashMap<String, String>();
			
			for(int i = 0; i < arr.length; i++) {
				String[] tempArr = arr[i].split("=");
				
				if(tempArr.length == 1) map.put(tempArr[0], "");
				else map.put(tempArr[0], tempArr[1]);
			}
			
			if(Long.parseLong(map.get("reject_end_date")) - System.currentTimeMillis() > 0 || Boolean.parseBoolean(map.get("forever_reject"))) return false;
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public Jws<Claims> getToken(String token) {
		byte[] hashingKey = hashing.hashing(key.getBytes()).getBytes();
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(hashingKey).parseClaimsJws(token);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return claims;
	}
	
}
