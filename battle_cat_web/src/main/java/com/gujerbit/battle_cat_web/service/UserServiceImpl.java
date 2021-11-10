package com.gujerbit.battle_cat_web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UserDAO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public String login(Map<String, String> map) {
		return dao.login(map);
	}
	
	@Override
	public int register(UserVO vo) {
		return dao.register(vo);
	}
	
	@Override
	public String selectSalt(String id) {
		return dao.selectSalt(id);
	}
	
	@Override
	public String selectID(String id) {
		return dao.selectID(id);
	}
	
}
