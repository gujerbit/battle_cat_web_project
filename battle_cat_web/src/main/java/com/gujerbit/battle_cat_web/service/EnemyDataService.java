package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

public interface EnemyDataService {

	List<EnemyDataVO> selectAllEnemyData(int start);
	
	int selectAllEnemyDataSize();
	
//	List<EnemyDataVO> selectSearchIncludeEnemyData(Map<String, ArrayList<String>> map);
//	
//	List<EnemyDataVO> selectSearchEnemyData(Map<String, ArrayList<String>> map);
//	
//	List<EnemyDataVO> selectNameSearchIncludeEnemyData(Map<String, ArrayList<String>> map);
//	
//	List<EnemyDataVO> selectNameSearchEnemyData(Map<String, ArrayList<String>> map);
//	
//	List<EnemyDataVO> selectNameEnemyData(String unitName);
//	
//	List<EnemyDataVO> selectIdEnemyData(String unitId);
	
}
