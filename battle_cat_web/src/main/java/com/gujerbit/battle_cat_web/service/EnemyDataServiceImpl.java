package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public List<EnemyDataVO> selectSearchIncludeEnemyData(Map<String, ArrayList<String>> map) {
		return dao.selectSearchIncludeEnemyData(map);
	}
	
	@Override
	public List<EnemyDataVO> selectSearchEnemyData(Map<String, ArrayList<String>> map) {
		return dao.selectSearchEnemyData(map);
	}
	
	@Override
	public List<EnemyDataVO> selectNameSearchIncludeEnemyData(Map<String, ArrayList<String>> map) {
		return dao.selectNameSearchIncludeEnemyData(map);
	}
	
	@Override
	public List<EnemyDataVO> selectNameSearchEnemyData(Map<String, ArrayList<String>> map) {
		return dao.selectNameSearchEnemyData(map);
	}
	
	@Override
	public List<EnemyDataVO> selectNameEnemyData(String unitName) {
		return dao.selectNameEnemyData(unitName);
	}
	
	@Override
	public List<EnemyDataVO> selectIdEnemyData(String unitId) {
		return dao.selectIdEnemyData(unitId);
	}
	
}
