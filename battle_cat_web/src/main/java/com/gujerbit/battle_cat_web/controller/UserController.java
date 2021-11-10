package com.gujerbit.battle_cat_web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.MailServiceImpl;
import com.gujerbit.battle_cat_web.service.SessionServiceImpl;
import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.util.Hashing;
import com.gujerbit.battle_cat_web.vo.UserVO;

@CrossOrigin("*")
@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private SessionServiceImpl sessionService;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@Autowired
	private Hashing hashing;
	
	@GetMapping("/login")
	public String init() {
		return "";
	}
	
	@PostMapping("/login_process")
	public @ResponseBody ResponseEntity<Map<String, Object>> loginProcess(@RequestBody UserVO vo, HttpServletResponse res) {
		String id = vo.getEmail();
		String password = vo.getPassword();
		String digestPassword = hashing.hashing(password.getBytes());
		String salt = userService.selectSalt(id);
		Map<String, String> map = new HashMap<>();
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		map.put("id", id);
		map.put("password", saltingPassword);
		String name = "";
		name = userService.login(map);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		if(name != null) {
			try {
				vo.setEmail(id);
				vo.setPassword(saltingPassword);
				vo.setName(name);
				String token = sessionService.createToken(vo);
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", vo);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
		} else {
			resultMap.put("status", false);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PostMapping("/register_process")
	public @ResponseBody int registerProcess(@RequestBody UserVO vo) {
		String password = vo.getPassword();
		String digestPassword = hashing.hashing(password.getBytes());
		String code = vo.getCode();
		String grade = "user";
		String salt = hashing.createSalt();
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		
		vo.setPassword(saltingPassword);
		vo.setCode(hashing.hashing(code.getBytes()));
		vo.setGrade(grade);
		vo.setSalt(salt);
		
		return userService.register(vo);
	}
	
	@PostMapping("/register_code")
	public @ResponseBody String sendRegisterCode(@RequestBody UserVO vo) {
		return mailService.mailSend(vo.getEmail());
	}
	
	@PostMapping("/check_id")
	public @ResponseBody boolean checkID(@RequestBody UserVO vo) {
		return userService.selectID(vo.getEmail()) != null ? false : true;
	}
	
}
