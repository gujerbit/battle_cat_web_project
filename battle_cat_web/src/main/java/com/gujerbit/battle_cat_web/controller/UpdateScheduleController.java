package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.UpdateScheduleService;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@CrossOrigin(origins = "*")
@Controller
public class UpdateScheduleController {
	
	@Autowired
	private UpdateScheduleService service;
	
	@GetMapping("/update_schedule_list/{limit}")
	public @ResponseBody List<UpdateScheduleVO> getUpdateScheduleTitle(@PathVariable int limit) {
		List<UpdateScheduleVO> list = service.selectUpdateScheduleTitle(limit);
		
		return list;
	}
	
	@GetMapping("/update_schedule_data/{version}")
	public @ResponseBody List<UpdateScheduleVO> getUpdateScheduleData(@PathVariable String version) {
		String versionData = version;
		List<UpdateScheduleVO> list = service.selectUpdateSchedule(versionData);
		return list;
	}
	
	@GetMapping("/update_schedule_list_size")
	public @ResponseBody int getUpdateScheduleSize() {
		int size = service.selectUpdateScheduleSize();
		
		return size;
	}
	
}