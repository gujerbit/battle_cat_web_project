package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;
import com.gujerbit.battle_cat_web.vo.PatchNoteVO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@Mapper
public interface ScheduleDAO {
	
	public List<DailyScheduleVO> selectDailySchedule();
	
	public List<EventScheduleVO> selectEventSchedule();
	
	public List<GachaScheduleVO> selectGachaSchedule();
	
	public List<UpdateScheduleVO> selectUpdateSchedule();
	
	public List<PatchNoteVO> selectPatchNote();
	
}
