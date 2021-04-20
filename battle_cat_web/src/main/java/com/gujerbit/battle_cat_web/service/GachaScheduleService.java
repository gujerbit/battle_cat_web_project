package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.GachaScheduleDAO;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;

@Service
public class GachaScheduleService {

	@Autowired
	private GachaScheduleDAO instance;
	
	public List<GachaScheduleVO> selectGachaSchedule() {
		return instance.selectGachaSchedule();
	}
	
}
