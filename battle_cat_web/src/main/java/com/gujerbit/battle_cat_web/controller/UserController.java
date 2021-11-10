package com.gujerbit.battle_cat_web.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

import com.gujerbit.battle_cat_web.service.SessionServiceImpl;
import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.vo.UserVO;

@CrossOrigin("*")
@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private SessionServiceImpl sessionService;
	
	@GetMapping("/login")
	public String init() {
		return "";
	}
	
	@PostMapping("/login_process")
	public @ResponseBody ResponseEntity<Map<String, Object>> loginProcess(@RequestBody UserVO vo, HttpServletResponse res) {
		String id = vo.getId();
		String password = vo.getPassword();
		String digestPassword = hashing(password.getBytes());
		String salt = userService.selectSalt(id);
		Map<String, String> map = new HashMap<>();
		String saltingPassword = hashing((digestPassword + salt).getBytes());
		map.put("id", id);
		map.put("password", saltingPassword);
		String name = "";
		name = userService.login(map);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		if(name != null) {
			try {
				vo.setId(id);
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
		String digestPassword = hashing(password.getBytes());
		String code = vo.getCode();
		String grade = "user";
		String salt = createSalt();
		String saltingPassword = hashing((digestPassword + salt).getBytes());
		
		vo.setPassword(saltingPassword);
		vo.setCode(hashing(code.getBytes()));
		vo.setGrade(grade);
		vo.setSalt(salt);
		
		return userService.register(vo);
	}
	
	@PostMapping("/check_id")
	public @ResponseBody boolean checkID(@RequestBody UserVO vo) {
		return userService.selectID(vo.getId()) != null ? false : true;
	}
	
	private String hashing(byte[] value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			for(int i = 0; i < 77; i++) {
				 String temp = byteToString(value);
				 md.update(temp.getBytes());
				 value = md.digest();
			}
			
			return byteToString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String byteToString(byte[] value) {
		StringBuffer buffer = new StringBuffer();
		
		for(byte item : value) buffer.append(String.format("%02x", item));
		
		return buffer.toString();
	}
	
	private String createSalt() {
		String result = "";
		
		for(int i = 0; i < 16; i++) {
			Random rnd = new Random();
			int rndNum = rnd.nextInt(3);
			
			if(rndNum == 0) {
				result += String.valueOf((char)(rnd.nextInt(26) + 97));
			} else if(rndNum == 1) {
				result += String.valueOf((char)(rnd.nextInt(26) + 65));
			} else {
				result += String.valueOf(rnd.nextInt(10));
			}
		}
		
		return hashing(result.getBytes());
	}
	
}
