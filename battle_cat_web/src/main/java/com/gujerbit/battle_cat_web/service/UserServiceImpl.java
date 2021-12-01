package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public ArrayList<UserVO> findEmail() {
		return dao.findEmail();
	}
	
	@Override
	public int changePassword(UserVO vo) {
		return dao.changePassword(vo);
	}
	
	@Override
	public UserVO selectUserInfo(String name) {
		return dao.selectUserInfo(name);
	}
	
	@Override
	public int descriptionChange(UserVO vo) {
		return dao.descriptionChange(vo);
	}
	
	@Override
	public int nameChange(UserVO vo) {
		return dao.nameChange(vo);
	}
	
	@Override
	public int codeChange(UserVO vo) {
		return dao.codeChange(vo);
	}
	
	@Override
	public int profileImgChange(UserVO vo) {
		return dao.profileImgChange(vo);
	}
	
	@Override
	public List<UserVO> selectAllUserData() {
		return dao.selectAllUserData();
	}
	
	@Override
	public int userRemove(UserVO vo) {
		return dao.userRemove(vo);
	}
	
	@Override
	public UserVO checkReject(UserVO vo) {
		return dao.checkReject(vo);
	}
	
	@Override
	public String checkGrade(UserVO vo) {
		return dao.checkGrade(vo);
	}
	
	@Override
	public List<String> userProfileImgCheck() {
		return dao.userProfileImgCheck();
	}
	
}
