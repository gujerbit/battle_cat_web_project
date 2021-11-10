package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.ScheduleDAO;
import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;
import com.gujerbit.battle_cat_web.vo.PatchNoteVO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleDAO dao;
	
	@Override
	public List<DailyScheduleVO> selectDailySchedule() {
		return dao.selectDailySchedule();
	}
	
	@Override
	public List<EventScheduleVO> selectEventSchedule() {
		return dao.selectEventSchedule();
	}
	
	@Override
	public List<GachaScheduleVO> selectGachaSchedule() {
		return dao.selectGachaSchedule();
	}
	
	@Override
	public List<UpdateScheduleVO> selectUpdateSchedule() {
		return dao.selectUpdateSchedule();
	}
	
	@Override
	public List<PatchNoteVO> selectPatchNote() {
		return dao.selectPatchNote();
	}

}
