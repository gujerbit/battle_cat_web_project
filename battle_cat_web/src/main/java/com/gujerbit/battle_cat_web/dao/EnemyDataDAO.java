package com.gujerbit.battle_cat_web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

@Mapper
public interface EnemyDataDAO {

	public List<EnemyDataVO> selectAllEnemyData();
	
	public List<EnemyDataVO> selectSearchIncludeEnemyData(Map<String, ArrayList<String>> map);
	
	public List<EnemyDataVO> selectSearchEnemyData(Map<String, ArrayList<String>> map);
	
	public List<EnemyDataVO> selectNameSearchIncludeEnemyData(Map<String, ArrayList<String>> map);
	
	public List<EnemyDataVO> selectNameSearchEnemyData(Map<String, ArrayList<String>> map);
	
	public List<EnemyDataVO> selectNameEnemyData(String unitName);
	
	public List<EnemyDataVO> selectIdEnemyData(String unitId);
	
}
