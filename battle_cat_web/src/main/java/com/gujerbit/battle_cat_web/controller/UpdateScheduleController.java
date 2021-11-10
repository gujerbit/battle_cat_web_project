package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.UpdateScheduleServiceImpl;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@CrossOrigin("*")
@Controller
public class UpdateScheduleController {

	@Autowired
	private UpdateScheduleServiceImpl service;
	
	@GetMapping("/update_schedule_data/{limit}")
	public @ResponseBody List<UpdateScheduleVO> getUpdateScheduleData(@PathVariable int limit) {
		List<UpdateScheduleVO> list = service.selectUpdateScheduleData(limit);
		
		return list;
	}
	
	@GetMapping("/update_schedule_size_data")
	public @ResponseBody int getUpdateScheduleSizeData() {
		int size = service.selectUpdateScheduleSizeData();
		
		return size;
	}
	
}
