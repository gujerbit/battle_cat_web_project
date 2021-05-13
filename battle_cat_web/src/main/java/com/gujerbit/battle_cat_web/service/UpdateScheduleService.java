package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UpdateScheduleDAO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@Service
public class UpdateScheduleService {
	
	@Autowired
	UpdateScheduleDAO dao;
	
	public List<UpdateScheduleVO> selectUpdateScheduleTitle(int limit) {
		return dao.selectUpdateScheduleTitle(limit);
	}
	
	public List<UpdateScheduleVO> selectUpdateSchedule(String version) {
		return dao.selectUpdateSchedule(version);
	}
	
	public int selectUpdateScheduleSize() {
		return dao.selectUpdateScheduleSize();
	}
	
}
