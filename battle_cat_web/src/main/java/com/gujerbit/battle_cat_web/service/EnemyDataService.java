package com.gujerbit.battle_cat_web.service;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

public interface EnemyDataService {

	List<EnemyDataVO> selectAllEnemyData();
	
}
