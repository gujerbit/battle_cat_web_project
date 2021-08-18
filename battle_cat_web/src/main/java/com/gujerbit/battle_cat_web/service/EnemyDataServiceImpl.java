package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.EnemyDataDAO;
import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

@Service
public class EnemyDataServiceImpl implements EnemyDataService {

	@Autowired
	private EnemyDataDAO dao;
	
	@Override
	public List<EnemyDataVO> selectAllEnemyData() {
		return dao.selectAllEnemyData();
	}
	
}
