package com.gujerbit.battle_cat_web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
//	@GetMapping("/unit_data_search/{searchData}")
//	public @ResponseBody List<UnitDataVO> getSearch(@PathVariable String[] searchData) {
//		List<UnitDataVO> list = service.selectUnitData(searchData);
//		
//		for(UnitDataVO vo : list) System.out.println(vo.toString());
//		
//		return list;
//	}
	
	@GetMapping("/unit_data")
	public @ResponseBody List<UnitDataVO> getAllUnitData() {
		List<UnitDataVO> list = service.selectAllUnitData();
		
		return list;
	}
	
	@GetMapping("/unit_data_search/{searchData}")
	public @ResponseBody List<UnitDataVO> getSearchUnitData(@PathVariable String[] searchData) {
		ArrayList<String> propertys = new ArrayList<>();
		ArrayList<String> raritys = new ArrayList<>();
		ArrayList<String> targets = new ArrayList<>();
		ArrayList<String> attackTypes = new ArrayList<>();
		
		for(String value : searchData) {
			if(value.contains("rarity_")) {
				raritys.add(value.substring(7));
			} else if(value.contains("target_") && !value.contains("target_only")) {
				targets.add(value.substring(7));
			} else if(value.contains("attack_type_")) {
				attackTypes.add(value.substring(12));
			} else {
				propertys.add(value);
			}
		}
		
		Map<String, ArrayList<String>> map = new HashMap<>();
		map.put("propertys", propertys);
		map.put("raritys", raritys);
		map.put("targets", targets);
		map.put("attackTypes", attackTypes);
		List<UnitDataVO> list = service.selectSearchUnitData(map);
		
		return list;
	}
	
}
