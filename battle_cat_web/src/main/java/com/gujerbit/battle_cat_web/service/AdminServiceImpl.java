package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.AdminDAO;
import com.gujerbit.battle_cat_web.vo.AdminLogVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	@Override
	public int userReject(UserVO vo) {
		return dao.userReject(vo);
	}
	
	@Override
	public int userForeverReject(UserVO vo) {
		return dao.userForeverReject(vo);
	}
	
	@Override
	public int userRejectRelease(UserVO vo) {
		return dao.userRejectRelease(vo);
	}
	
	@Override
	public int setAdminLog(AdminLogVO vo) {
		return dao.setAdminLog(vo);
	}
	
	@Override
	public List<AdminLogVO> getAdminLog() {
		return dao.getAdminLog();
	}
	
	@Override
	public int userGradeSetting(UserVO vo) {
		return dao.userGradeSetting(vo);
	}
	
}
