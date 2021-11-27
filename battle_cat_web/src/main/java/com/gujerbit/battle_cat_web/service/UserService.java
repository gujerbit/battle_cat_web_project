package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;

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
	
	UserVO selectUserInfo(String name);
	
	int descriptionChange(UserVO vo);
	
	int nameChange(UserVO vo);
	
	int codeChange(UserVO vo);
	
	int profileImgChange(UserVO vo);
	
	List<UserVO> selectAllUserData();
	
	int userRemove(UserVO vo);
	
	UserVO checkReject(UserVO vo);
	
	String checkGrade(UserVO vo);

}
