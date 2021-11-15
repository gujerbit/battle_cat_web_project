package com.gujerbit.battle_cat_web.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.UserVO;

@Mapper
public interface UserDAO {

	public UserVO login(UserVO vo);
	
	public int register(UserVO vo);
	
	public String selectSalt(String id);
	
	public String selectEmail(String id);
	
	public String selectName(String name);
	
	public ArrayList<String> selectCode();
	
	public ArrayList<UserVO> findEmail();
	
	public int changePassword(UserVO vo);
	
	public UserVO selectUserInfo(String name);
	
	public int descriptionChange(UserVO vo);
	
}
