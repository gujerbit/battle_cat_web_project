package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gujerbit.battle_cat_web.vo.UnitDataVO;

public interface UnitDataService {

	List<UnitDataVO> selectAllUnitData();
	
	List<UnitDataVO> selectSearchIncludeUnitData(Map<String, ArrayList<String>> map);
	
	List<UnitDataVO> selectSearchUnitData(Map<String, ArrayList<String>> map);
	
	List<UnitDataVO> selectNameSearchIncludeUnitData(Map<String, ArrayList<String>> map);
	
	List<UnitDataVO> selectNameSearchUnitData(Map<String, ArrayList<String>> map);
	
	List<UnitDataVO> selectNameUnitData(String unitName);
	
	List<UnitDataVO> selectIdUnitData(String unitId);
	
}
