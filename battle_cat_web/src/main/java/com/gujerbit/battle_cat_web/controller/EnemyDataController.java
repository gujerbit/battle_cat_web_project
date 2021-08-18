package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.EnemyDataServiceImpl;
import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

@CrossOrigin("*")
@Controller
public class EnemyDataController {

	@Autowired
	private EnemyDataServiceImpl service;
	
	@GetMapping("/enemy_data")
	public @ResponseBody List<EnemyDataVO> getAllEnemyData() {
		List<EnemyDataVO> list = service.selectAllEnemyData();
		
		return list;
	}
	
}
