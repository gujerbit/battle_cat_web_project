package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UserDAO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public UserVO login(UserVO vo) {
		return dao.login(vo);
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
	public String selectEmail(String id) {
		return dao.selectEmail(id);
	}
	
	@Override
	public String selectName(String name) {
		return dao.selectName(name);
	}
	
	@Override
	public ArrayList<String> selectCode() {
		return dao.selectCode();
	}
	
}
