package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.GachaScheduleService;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;

@Controller
@CrossOrigin("*")
public class GachaScheduleController {

	@Autowired
	private GachaScheduleService service;
	
	@GetMapping("/gacha_schedule")
	public @ResponseBody List<GachaScheduleVO> getGachaSchedule() {
		List<GachaScheduleVO> list = service.selectGachaSchedule();
		
		return list;
	}
	
}
