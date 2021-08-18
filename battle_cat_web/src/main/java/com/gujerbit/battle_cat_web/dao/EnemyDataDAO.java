package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

@Mapper
public interface EnemyDataDAO {

	public List<EnemyDataVO> selectAllEnemyData();
	
}
