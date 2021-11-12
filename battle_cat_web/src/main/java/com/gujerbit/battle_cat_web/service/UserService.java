package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;

import com.gujerbit.battle_cat_web.vo.UserVO;

public interface UserService {
	
	UserVO login(UserVO vo);
	
	int register(UserVO vo);
	
	String selectSalt(String id);
	
	String selectEmail(String id);
	
	String selectName(String name);
	
	ArrayList<String> selectCode();
	
	ArrayList<UserVO> findEmail();
	
	int changePassword(UserVO vo);

}
