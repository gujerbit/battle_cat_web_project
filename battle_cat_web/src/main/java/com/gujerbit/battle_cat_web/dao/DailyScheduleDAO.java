package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;

@Mapper
public interface DailyScheduleDAO {
	
	public List<DailyScheduleVO> selectDailySchedule(int week);
	
}
