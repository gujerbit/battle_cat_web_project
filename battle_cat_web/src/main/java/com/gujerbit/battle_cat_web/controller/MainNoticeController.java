package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.UpdateVersionService;
import com.gujerbit.battle_cat_web.vo.UpdateVersionVO;

@Controller
public class MainNoticeController {
	
	@Autowired
	private UpdateVersionService updateVersionService;
	
	@GetMapping("/")
	public @ResponseBody List<UpdateVersionVO> getUpdateVersion() {
		List<UpdateVersionVO> updateVersionList = updateVersionService.selectUpdateVersion();
		
		return updateVersionList;
	}
	
}
