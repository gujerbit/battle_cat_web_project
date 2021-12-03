package com.gujerbit.battle_cat_web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.AdminServiceImpl;
import com.gujerbit.battle_cat_web.vo.AdminLogVO;
import com.gujerbit.battle_cat_web.vo.UserVO;

@CrossOrigin("*")
@Controller
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/user_reject")
	public @ResponseBody int userReject(@RequestBody UserVO vo) {
		return adminService.userReject(vo);
	}
	
	@PostMapping("/user_forever_reject")
	public @ResponseBody int userForeverReject(@RequestBody UserVO vo) {
		return adminService.userForeverReject(vo);
	}
	
	@PostMapping("/user_reject_release")
	public @ResponseBody int userRejectRelease(@RequestBody UserVO vo) {
		return adminService.userRejectRelease(vo);
	}
	
	@PostMapping("/set_admin_log")
	public @ResponseBody int setAdminLog(@RequestBody AdminLogVO vo) {
		return adminService.setAdminLog(vo);
	}
	
	@GetMapping("/get_admin_log/{size}")
	public @ResponseBody List<AdminLogVO> getAdminLog(@PathVariable int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", size);
		map.put("end", size + 99);
		
		return adminService.getAdminLog(map);
	}
	
	@GetMapping("/get_admin_log_size")
	public @ResponseBody int getAdminLogSize() {
		return adminService.selectAdminLogSize();
	}
	
	@PostMapping("/user_grade_setting")
	public @ResponseBody int userGradeSetting(@RequestBody UserVO vo) {
		return adminService.userGradeSetting(vo);
	}
	
}
