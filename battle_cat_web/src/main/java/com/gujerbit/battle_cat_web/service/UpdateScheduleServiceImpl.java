package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UpdateScheduleDAO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@Service
public class UpdateScheduleServiceImpl implements UpdateScheduleService {

	@Autowired
	private UpdateScheduleDAO dao;
	
	@Override
	public List<UpdateScheduleVO> selectUpdateScheduleData(int limit) {
		return dao.selectUpdateScheduleData(limit);
	}
	
	@Override
	public int selectUpdateScheduleSizeData() {
		return dao.selectUpdateScheduleSizeData();
	}
	
}
