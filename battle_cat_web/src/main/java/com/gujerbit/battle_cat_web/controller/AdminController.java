package com.gujerbit.battle_cat_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
}
