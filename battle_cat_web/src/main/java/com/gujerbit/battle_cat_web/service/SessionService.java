package com.gujerbit.battle_cat_web.service;

import com.gujerbit.battle_cat_web.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface SessionService {
	
	String createToken(UserVO vo);
	
	boolean checkToken(String token, String path);
	
	Jws<Claims> getToken(String token);

}
