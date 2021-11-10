package com.gujerbit.battle_cat_web.service;

import java.util.Map;

import com.gujerbit.battle_cat_web.vo.UserVO;

public interface UserService {
	
	String login(Map<String, String> map);
	
	int register(UserVO vo);
	
	String selectSalt(String id);
	
	String selectID(String id);

}
