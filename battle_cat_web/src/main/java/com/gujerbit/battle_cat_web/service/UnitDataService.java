package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UnitDataDAO;
import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@Service
public class UnitDataService {

	@Autowired
	private UnitDataDAO dao;
	
	public List<UnitDataVO> selectAllUnitData() {
		return dao.selectAllUnitData();
	}
	
	public List<UnitDataVO> selectSearchIncludeUnitData(Map<String, ArrayList<String>> map) {
		return dao.selectSearchIncludeUnitData(map);
	}
	
	public List<UnitDataVO> selectSearchUnitData(Map<String, ArrayList<String>> map) {
		return dao.selectSearchUnitData(map);
	}
	
	public List<UnitDataVO> selectNameUnitData(String unitName) {
		return dao.selectNameUnitData(unitName);
	}
	
	public List<UnitDataVO> selectIdUnitData(String unitId) {
		return dao.selectIdUnitData(unitId);
	}
	
}
