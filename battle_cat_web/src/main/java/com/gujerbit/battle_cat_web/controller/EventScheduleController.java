package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.EventScheduleService;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;

@CrossOrigin("*")
@Controller
public class EventScheduleController {

	@Autowired
	private EventScheduleService service;
	
	@GetMapping("/event_schedule_list/{limit}")
	public @ResponseBody List<EventScheduleVO> getEventSchedule(@PathVariable int limit) {
		List<EventScheduleVO> list = service.selectEventSchedule(limit);
		
		return list;
	}
	
	@GetMapping("/event_schedule_list_size")
	public @ResponseBody int getEventScheduleSize() {
		int size = service.selectEventScheduleSize();
		
		return size;
	}
	
}
