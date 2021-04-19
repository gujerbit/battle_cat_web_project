package com.gujerbit.battle_cat_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UpdateVersionDAO;
import com.gujerbit.battle_cat_web.vo.UpdateVersionVO;

@Service
public class UpdateVersionService {
	
	@Autowired
	UpdateVersionDAO instance;
	
	public List<UpdateVersionVO> selectUpdateVersionTitle() {
		return instance.selectUpdateVersionTitle();
	}
	
	public List<UpdateVersionVO> selectUpdateVersion(String version) {
		return instance.selectUpdateVersion(version);
	}
	
}
