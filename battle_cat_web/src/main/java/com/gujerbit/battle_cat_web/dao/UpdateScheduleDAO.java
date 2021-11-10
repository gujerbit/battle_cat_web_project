package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@Mapper
public interface UpdateScheduleDAO {

	public List<UpdateScheduleVO> selectUpdateScheduleData(int limit);

	public int selectUpdateScheduleSizeData(); 
	
}
