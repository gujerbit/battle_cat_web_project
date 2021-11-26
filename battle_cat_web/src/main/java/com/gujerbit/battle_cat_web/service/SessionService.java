package com.gujerbit.battle_cat_web.service;

import java.util.Map;

import com.gujerbit.battle_cat_web.vo.UserVO;

public interface SessionService {
	
	String createToken(UserVO vo);
	
	boolean checkToken(String token);
	
	Map<String, Object> getToken(String token);

}
