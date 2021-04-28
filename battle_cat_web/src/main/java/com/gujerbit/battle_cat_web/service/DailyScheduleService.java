package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.DailyScheduleDAO;
import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;

@Service
public class DailyScheduleService {
	
	@Autowired
	private DailyScheduleDAO dao;
	
	public List<DailyScheduleVO> selectDailySchedule(int week) {
		return dao.selectDailySchedule(week);
	}
	
}
