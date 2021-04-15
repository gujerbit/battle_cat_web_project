package com.gujerbit.battle_cat_web.dao;

import java.util.List;

import com.gujerbit.battle_cat_web.vo.UpdateVersionVO;

public interface UpdateVersionDAO {
	public List<UpdateVersionVO> selectUpdateVersion();
}
