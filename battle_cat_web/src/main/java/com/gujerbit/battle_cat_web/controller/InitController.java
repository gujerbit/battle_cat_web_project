package com.gujerbit.battle_cat_web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.MemberService;
import com.gujerbit.battle_cat_web.vo.MemberVO;
import com.gujerbit.battle_cat_web.vo.TempVO;

@Controller
public class InitController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/msg")
	public @ResponseBody Map<String, Object> initConsole() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "Hello Spring Boot & Vue.js!");
		map.put("name", "yeongjun");
		map.put("age", 19);
		
		return map;
	}
	
	@RequestMapping(value="msg", method=RequestMethod.POST)
	public @ResponseBody Map<?, ?> returnMsg(@RequestBody TempVO vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", vo.getMsg());
		map.put("age", vo.getAge());
		System.out.println(vo.toString());
		
		return map;
	}
	
	@GetMapping("/db")
	public @ResponseBody List<MemberVO> selectListMembers() {
		List<MemberVO> list = service.selectAllMembers();
		return list;
	}
	
}