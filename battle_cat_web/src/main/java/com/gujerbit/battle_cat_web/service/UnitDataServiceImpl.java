package com.gujerbit.battle_cat_web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UnitDataDAO;
import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@Service
public class UnitDataServiceImpl implements UnitDataService {

	@Autowired
	private UnitDataDAO dao;
	
	@Override
	public List<UnitDataVO> selectAllUnitData(int start) {
		return dao.selectAllUnitData(start);
	}
	
	@Override
	public int selectAllUnitDataSize() {
		return dao.selectAllUnitDataSize();
	}
	
//	@Override
//	public List<UnitDataVO> selectSearchIncludeUnitData(Map<String, ArrayList<String>> map) {
//		return dao.selectSearchIncludeUnitData(map);
//	}
//	
//	@Override
//	public List<UnitDataVO> selectSearchUnitData(Map<String, ArrayList<String>> map) {
//		return dao.selectSearchUnitData(map);
//	}
//	
//	@Override
//	public List<UnitDataVO> selectNameSearchIncludeUnitData(Map<String, ArrayList<String>> map) {
//		return dao.selectNameSearchIncludeUnitData(map);
//	}
//	
//	@Override
//	public List<UnitDataVO> selectNameSearchUnitData(Map<String, ArrayList<String>> map) {
//		return dao.selectNameSearchUnitData(map);
//	}
//	
//	@Override
//	public List<UnitDataVO> selectNameUnitData(String unitName) {
//		return dao.selectNameUnitData(unitName);
//	}
//	
//	@Override
//	public List<UnitDataVO> selectIdUnitData(String unitId) {
//		return dao.selectIdUnitData(unitId);
//	}
	
}
