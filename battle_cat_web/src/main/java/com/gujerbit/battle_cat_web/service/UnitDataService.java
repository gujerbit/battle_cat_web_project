package com.gujerbit.battle_cat_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.battle_cat_web.dao.UnitDataDAO;

@Service
public class UnitDataService {

	@Autowired
	private UnitDataDAO dao;
	
}
