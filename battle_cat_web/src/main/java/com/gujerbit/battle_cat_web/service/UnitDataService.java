package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UnitDataDAO;
import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@Service
public class UnitDataService {

	@Autowired
	private UnitDataDAO dao;
	
	public List<UnitDataVO> selectUnitData(String[] searchData) {
		return dao.selectUnitData(searchData);
	}
	
}
