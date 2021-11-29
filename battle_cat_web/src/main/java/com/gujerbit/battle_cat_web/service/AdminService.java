package com.gujerbit.battle_cat_web.service;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.AdminLogVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

public interface AdminService {
	
	int userReject(UserVO vo);
	
	int userForeverReject(UserVO vo);
	
	int userRejectRelease(UserVO vo);
	
	int setAdminLog(AdminLogVO vo);
	
	List<AdminLogVO> getAdminLog();
	
	int userGradeSetting(UserVO vo);

}