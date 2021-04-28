package com.gujerbit.battle_cat_web.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.DailyScheduleService;
import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;

@CrossOrigin("*")
@Controller
public class DailyScheduleController {
	
	@Autowired
	private DailyScheduleService service;
	
	@GetMapping("/daily_schedule_list")
	public @ResponseBody List<DailyScheduleVO> getDailySchedule() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(calendar.DAY_OF_WEEK);
		System.out.println(week);
		
		List<DailyScheduleVO> list = service.selectDailySchedule(week);
		
		return list;
	}
	
}
