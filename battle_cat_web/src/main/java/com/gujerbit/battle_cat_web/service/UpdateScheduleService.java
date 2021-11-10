package com.gujerbit.battle_cat_web.service;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

public interface UpdateScheduleService {

	List<UpdateScheduleVO> selectUpdateScheduleData(int limit);
	
	int selectUpdateScheduleSizeData();
	
}
