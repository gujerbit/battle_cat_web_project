package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.EventScheduleVO;

@Mapper
public interface EventScheduleDAO {
	public List<EventScheduleVO> selectEventSchedule(int limit);
	
	public int selectEventScheduleSize();
}
