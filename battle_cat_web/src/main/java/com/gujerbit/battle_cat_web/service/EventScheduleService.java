package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.EventScheduleDAO;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;

@Service
public class EventScheduleService {
	
	@Autowired
	private EventScheduleDAO dao;
	
	public List<EventScheduleVO> selectEventSchedule(int limit) {
		return dao.selectEventSchedule(limit);
	}
	
	public int selectEventScheduleSize() {
		return dao.selectEventScheduleSize();
	}
	
}
