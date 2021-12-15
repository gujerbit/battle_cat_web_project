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

import com.gujerbit.battle_cat_web.service.EnemyDataServiceImpl;
import com.gujerbit.battle_cat_web.vo.EnemyDataVO;

@CrossOrigin("*")
@Controller
public class EnemyDataController {

	@Autowired
	private EnemyDataServiceImpl service;
	
	@GetMapping("/enemy_data/{start}")
	public @ResponseBody List<EnemyDataVO> getAllEnemyData(@PathVariable int start) {
		List<EnemyDataVO> list = service.selectAllEnemyData(start);
		
		return list;
	}
	
	@GetMapping("/enemy_data_size")
	public @ResponseBody int getAllEnemyDataSize() {
		return service.selectAllEnemyDataSize();
	}
	
//	@GetMapping("/enemy_data_search_include/{searchData}")
//	public @ResponseBody List<EnemyDataVO> getSearchIncludeEnemyData(@PathVariable String[] searchData) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		
//		for(String value : searchData) {
//			if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type")) {
//				attackTypes.add(value.substring(12));
//			} else {
//				propertys.add(value);
//			}
//		}
//		
//		Map<String, ArrayList<String>> map = new HashMap<>();
//		map.put("propertys", propertys);
//		map.put("targets", targets);
//		map.put("attackTypes", attackTypes);
//		List<EnemyDataVO> list = service.selectSearchIncludeEnemyData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/enemy_data_search/{searchData}")
//	public @ResponseBody List<EnemyDataVO> getSearchEnemyData(@PathVariable String[] searchData) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		
//		for(String value : searchData) {
//			if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type")) {
//				attackTypes.add(value.substring(12));
//			} else {
//				propertys.add(value);
//			}
//		}
//		
//		Map<String, ArrayList<String>> map = new HashMap<>();
//		map.put("propertys", propertys);
//		map.put("targets", targets);
//		map.put("attackTypes", attackTypes);
//		List<EnemyDataVO> list = service.selectSearchEnemyData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/enemy_data_name_search_include/{searchInfo}")
//	public @ResponseBody List<EnemyDataVO> getNameSearchIncludeEnemyData(@PathVariable String[] searchInfo) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> raritys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		ArrayList<String> unitName = new ArrayList<>();
//		
//		for(String value : searchInfo) {
//			if(value.contains("rarity_")) {
//				raritys.add(value.substring(7));
//			} else if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type_")) {
//				attackTypes.add(value.substring(12));
//			} else if(value.contains("name_")) {
//				unitName.add(value.substring(5));
//			} else {
//				propertys.add(value);
//			}
//		}
//		
//		Map<String, ArrayList<String>> map = new HashMap<>();
//		map.put("propertys", propertys);
//		map.put("raritys", raritys);
//		map.put("targets", targets);
//		map.put("attackTypes", attackTypes);
//		map.put("unitName", unitName);
//		
//		List<EnemyDataVO> list = service.selectNameSearchIncludeEnemyData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/enemy_data_name_search/{searchInfo}")
//	public @ResponseBody List<EnemyDataVO> getNameSearchEnemyData(@PathVariable String[] searchInfo) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> raritys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		ArrayList<String> unitName = new ArrayList<>();
//		
//		for(String value : searchInfo) {
//			if(value.contains("rarity_")) {
//				raritys.add(value.substring(7));
//			} else if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type_")) {
//				attackTypes.add(value.substring(12));
//			} else if(value.contains("name_")) {
//				unitName.add(value.substring(5));
//			} else {
//				propertys.add(value);
//			}
//		}
//		
//		Map<String, ArrayList<String>> map = new HashMap<>();
//		map.put("propertys", propertys);
//		map.put("raritys", raritys);
//		map.put("targets", targets);
//		map.put("attackTypes", attackTypes);
//		map.put("unitName", unitName);
//		
//		List<EnemyDataVO> list = service.selectNameSearchEnemyData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/enemy_data_name/{unitName}")
//	public @ResponseBody List<EnemyDataVO> getNameEnemyData(@PathVariable String unitName) {
//		List<EnemyDataVO> list = service.selectNameEnemyData(unitName);
//		
//		return list;
//	}
//	
//	@GetMapping("/enemy_data_id/{unitId}")
//	public @ResponseBody List<EnemyDataVO> getIdEnemyData(@PathVariable String unitId) {
//		List<EnemyDataVO> list = service.selectIdEnemyData(unitId);
//		
//		return list;
//	}
	
}
