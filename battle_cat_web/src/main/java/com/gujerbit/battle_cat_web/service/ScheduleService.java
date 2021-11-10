package com.gujerbit.battle_cat_web.service;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;
import com.gujerbit.battle_cat_web.vo.PatchNoteVO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

public interface ScheduleService {
	
	List<DailyScheduleVO> selectDailySchedule();
	
	List<EventScheduleVO> selectEventSchedule();
	
	List<GachaScheduleVO> selectGachaSchedule();
	
	List<UpdateScheduleVO> selectUpdateSchedule();
	
	List<PatchNoteVO> selectPatchNote();

}
