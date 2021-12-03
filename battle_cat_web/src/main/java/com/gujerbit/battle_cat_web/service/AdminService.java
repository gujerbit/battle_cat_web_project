package com.gujerbit.battle_cat_web.service;

import java.util.List;
import java.util.Map;

import com.gujerbit.battle_cat_web.vo.AdminLogVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

public interface AdminService {
	
	int userReject(UserVO vo);
	
	int userForeverReject(UserVO vo);
	
	int userRejectRelease(UserVO vo);
	
	int setAdminLog(AdminLogVO vo);
	
	List<AdminLogVO> getAdminLog(Map<String, Integer> size);
	
	int selectAdminLogSize();
	
	int userGradeSetting(UserVO vo);

}
