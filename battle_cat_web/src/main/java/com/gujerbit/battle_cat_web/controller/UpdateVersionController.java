package com.gujerbit.battle_cat_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.UpdateVersionService;
import com.gujerbit.battle_cat_web.vo.UpdateVersionVO;

@CrossOrigin(origins = "*")
@Controller
public class UpdateVersionController {
	
	@Autowired
	private UpdateVersionService service;
	
	@GetMapping("/main_update_version")
	public @ResponseBody List<UpdateVersionVO> getUpdateVersionTitle() {
		List<UpdateVersionVO> list = service.selectUpdateVersionTitle();
		
		return list;
	}
	
	@PostMapping("/update_version")
	public @ResponseBody void getUpdateVersionContent(@RequestBody String version) {
		String versionData = version.substring(0, version.length()-1);
		System.out.println(versionData);
	}
	
}
