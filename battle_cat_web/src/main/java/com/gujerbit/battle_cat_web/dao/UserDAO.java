package com.gujerbit.battle_cat_web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.UserVO;

@Mapper
public interface UserDAO {

	public String login(Map<String, String> map);
	
	public int register(UserVO vo);
	
	public String selectSalt(String id);
	
	public String selectID(String id);
	
}
