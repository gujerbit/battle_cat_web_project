package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.UnitDataService;
import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@CrossOrigin("*")
@Controller
public class UnitDataController {

	@Autowired
	private UnitDataService service;
	
	@GetMapping("/unit_data_search/{searchData}")
	public @ResponseBody List<UnitDataVO> getSearch(@PathVariable String[] searchData) {
		List<UnitDataVO> list = service.selectUnitData(searchData);
		
		for(UnitDataVO vo : list) System.out.println(vo.toString());
		
		return list;
	}
	
}
