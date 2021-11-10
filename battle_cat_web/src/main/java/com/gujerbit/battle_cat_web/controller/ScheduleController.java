package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.ScheduleServiceImpl;
import com.gujerbit.battle_cat_web.vo.DailyScheduleVO;
import com.gujerbit.battle_cat_web.vo.EventScheduleVO;
import com.gujerbit.battle_cat_web.vo.GachaScheduleVO;
import com.gujerbit.battle_cat_web.vo.PatchNoteVO;
import com.gujerbit.battle_cat_web.vo.UpdateScheduleVO;

@CrossOrigin("*")
@Controller
public class ScheduleController {

	@Autowired
	private ScheduleServiceImpl service;
	
	@GetMapping("/schedule/daily")
	public @ResponseBody List<DailyScheduleVO> getDailySchedule() {
		return service.selectDailySchedule();
	}
	
	@GetMapping("/schedule/event")
	public @ResponseBody List<EventScheduleVO> getEventSchedule() {
		return service.selectEventSchedule();
	}
	
	@GetMapping("/schedule/gacha")
	public @ResponseBody List<GachaScheduleVO> getGachaSchedule() {
		return service.selectGachaSchedule();
	}
	
	@GetMapping("/schedule/update")
	public @ResponseBody List<UpdateScheduleVO> getUpdateSchedule() {
		return service.selectUpdateSchedule();
	}
	
	@GetMapping("/schedule/patch_note")
	public @ResponseBody List<PatchNoteVO> getPatchNote() {
		return service.selectPatchNote();
	}
	
}
