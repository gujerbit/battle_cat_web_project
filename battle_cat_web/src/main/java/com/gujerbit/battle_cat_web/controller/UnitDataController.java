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

import com.gujerbit.battle_cat_web.service.UnitDataServiceImpl;
import com.gujerbit.battle_cat_web.vo.UnitDataVO;

@CrossOrigin("*")
@Controller
public class UnitDataController {

	@Autowired
	private UnitDataServiceImpl service;
	
	@GetMapping("/unit_data/{start}")
	public @ResponseBody List<UnitDataVO> getAllUnitData(@PathVariable int start) {
		List<UnitDataVO> list = service.selectAllUnitData(start);
		
		return list;
	}
	
	@GetMapping("/unit_data_size")
	public @ResponseBody int getAllUnitDataSize() {
		return service.selectAllUnitDataSize();
	}
	
//	@GetMapping("/unit_data_search_include/{searchData}")
//	public @ResponseBody List<UnitDataVO> getSearchIncludeUnitData(@PathVariable String[] searchData) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> raritys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		
//		for(String value : searchData) {
//			if(value.contains("rarity_")) {
//				raritys.add(value.substring(7));
//			} else if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type_")) {
//				attackTypes.add(value.substring(12));
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
//		
//		List<UnitDataVO> list = service.selectSearchIncludeUnitData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/unit_data_search/{searchData}")
//	public @ResponseBody List<UnitDataVO> getSearchUnitData(@PathVariable String[] searchData) {
//		ArrayList<String> propertys = new ArrayList<>();
//		ArrayList<String> raritys = new ArrayList<>();
//		ArrayList<String> targets = new ArrayList<>();
//		ArrayList<String> attackTypes = new ArrayList<>();
//		
//		for(String value : searchData) {
//			if(value.contains("rarity_")) {
//				raritys.add(value.substring(7));
//			} else if(value.contains("target_") && !value.contains("target_only")) {
//				targets.add(value.substring(7));
//			} else if(value.contains("attack_type_")) {
//				attackTypes.add(value.substring(12));
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
//		List<UnitDataVO> list = service.selectSearchUnitData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/unit_data_name_search_include/{searchInfo}")
//	public @ResponseBody List<UnitDataVO> getNameSearchIncludeUnitData(@PathVariable String[] searchInfo) {
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
//		List<UnitDataVO> list = service.selectNameSearchIncludeUnitData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/unit_data_name_search/{searchInfo}")
//	public @ResponseBody List<UnitDataVO> getNameSearchUnitData(@PathVariable String[] searchInfo) {
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
//		List<UnitDataVO> list = service.selectNameSearchUnitData(map);
//		
//		return list;
//	}
//	
//	@GetMapping("/unit_data_name/{unitName}")
//	public @ResponseBody List<UnitDataVO> getNameUnitData(@PathVariable String unitName) {
//		List<UnitDataVO> list = service.selectNameUnitData(unitName);
//		
//		return list;
//	}
//	
//	@GetMapping("/unit_data_id/{unitId}")
//	public @ResponseBody List<UnitDataVO> getIdUnitData(@PathVariable String unitId) {
//		List<UnitDataVO> list = service.selectIdUnitData(unitId);
//		
//		return list;
//	}
	
}
