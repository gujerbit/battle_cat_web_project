package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@Mapper
public interface UnitDataDAO {
	
	public List<UnitDataVO> selectUnitData(String[] searchData);
	
}
