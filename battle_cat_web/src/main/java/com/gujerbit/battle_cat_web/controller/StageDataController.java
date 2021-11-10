package com.gujerbit.battle_cat_web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.StageDataServiceImpl;
import com.gujerbit.battle_cat_web.vo.StageDataVO;

@CrossOrigin("*")
@Controller
public class StageDataController {

	@Autowired
	private StageDataServiceImpl service;
	
	@GetMapping("/stage_data_world")
	public @ResponseBody List<StageDataVO> getAllWorldStageData() {
		List<StageDataVO> list = service.selectAllWorldStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_future")
	public @ResponseBody List<StageDataVO> getAllFutureStageData() {
		List<StageDataVO> list = service.selectAllFutureStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_space")
	public @ResponseBody List<StageDataVO> getAllSpaceStageData() {
		List<StageDataVO> list = service.selectAllSpaceStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_legend")
	public @ResponseBody List<StageDataVO> getAllLegendStageData() {
		List<StageDataVO> list = service.selectAllLegendStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_origin_legend")
	public @ResponseBody List<StageDataVO> getAllOriginLegendStageData() {
		List<StageDataVO> list = service.selectAllOriginLegendStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_event")
	public @ResponseBody List<StageDataVO> getEventStageData() {
		List<StageDataVO> list = service.selectEventStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_guerrilla")
	public @ResponseBody List<StageDataVO> getGuerrillaStageData() {
		List<StageDataVO> list = service.selectGuerrillaStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_week/{week}")
	public @ResponseBody List<StageDataVO> getWeekStageData(@PathVariable String week) {
		List<StageDataVO> list = service.selectWeekStageData(week);
		
		return list;
	}
	
	@GetMapping("/stage_data_drop")
	public @ResponseBody List<StageDataVO> getDropStageData() {
		List<StageDataVO> list = service.selectDropStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_advent")
	public @ResponseBody List<StageDataVO> getAdventStageData() {
		List<StageDataVO> list = service.selectAdventStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_crazy")
	public @ResponseBody List<StageDataVO> getCrazyStageData() {
		List<StageDataVO> list = service.selectCrazyStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_cyclone")
	public @ResponseBody List<StageDataVO> getCycloneStageData() {
		List<StageDataVO> list = service.selectCycloneStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_arousal")
	public @ResponseBody List<StageDataVO> getArousalStageData() {
		List<StageDataVO> list = service.selectArousalStageData();
		
		return list;
	}
	
	@GetMapping("/stage_data_excavation/{grade}")
	public @ResponseBody List<StageDataVO> getExcavationStageData(@PathVariable String grade) {
		List<StageDataVO> list = service.selectExcavationStageData(grade);
		
		return list;
	}
	
	@GetMapping("/stage_data_id/{stageId}")
	public @ResponseBody List<StageDataVO> getIdStageData(@PathVariable String[] stageId) {
		Map<String, String> map = new HashMap<>();
		map.put("sortation", stageId[0]);
		map.put("stage", stageId[1]);
		List<StageDataVO> list = service.selectIdStageData(map);
		
		return list;
	}
	
	@GetMapping("/stage_data_name/{stageInfo}")
	public @ResponseBody List<StageDataVO> getNameStageData(@PathVariable String[] stageInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("sortation", stageInfo[0]);
		map.put("name", stageInfo[1]);
		List<StageDataVO> list = service.selectNameStageData(map);
		
		return list;
	}
	
}
