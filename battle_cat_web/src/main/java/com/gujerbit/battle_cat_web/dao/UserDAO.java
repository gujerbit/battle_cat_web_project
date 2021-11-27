package com.gujerbit.battle_cat_web.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public int nameChange(UserVO vo);
	
	public int codeChange(UserVO vo);
	
	public int profileImgChange(UserVO vo);
	
	public List<UserVO> selectAllUserData();
	
	public int userRemove(UserVO vo);
	
	public UserVO checkReject(UserVO vo);
	
	public String checkGrade(UserVO vo);
	
}
