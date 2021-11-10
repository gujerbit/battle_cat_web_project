package com.gujerbit.battle_cat_web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@Mapper
public interface UnitDataDAO {
	
	public List<UnitDataVO> selectAllUnitData();
	
	public List<UnitDataVO> selectSearchIncludeUnitData(Map<String, ArrayList<String>> map);
	
	public List<UnitDataVO> selectSearchUnitData(Map<String, ArrayList<String>> map);
	
	public List<UnitDataVO> selectNameSearchIncludeUnitData(Map<String, ArrayList<String>> map);
	
	public List<UnitDataVO> selectNameSearchUnitData(Map<String, ArrayList<String>> map);
	
	public List<UnitDataVO> selectNameUnitData(String unitName);
	
	public List<UnitDataVO> selectIdUnitData(String unitId);
	
}
