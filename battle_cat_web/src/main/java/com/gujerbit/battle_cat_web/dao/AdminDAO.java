package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.AdminLogVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Mapper
public interface AdminDAO {

	public int userReject(UserVO vo);
	
	public int userForeverReject(UserVO vo);
	
	public int userRejectRelease(UserVO vo);
	
	public int setAdminLog(AdminLogVO vo);
	
	public List<AdminLogVO> getAdminLog();
	
}
