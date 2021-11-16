package com.gujerbit.battle_cat_web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.battle_cat_web.service.MailServiceImpl;
import com.gujerbit.battle_cat_web.service.SessionServiceImpl;
import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.util.Hashing;
import com.gujerbit.battle_cat_web.util.RSA;
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
	
	@Autowired
	private RSA rsa;
	
	@GetMapping("/login")
	public String init() {
		return "";
	}
	
	@PostMapping("/login_process")
	public @ResponseBody ResponseEntity<Map<String, Object>> loginProcess(@RequestBody UserVO vo, HttpServletResponse res) {
		String password = vo.getPassword();
		String digestPassword = hashing.hashing(password.getBytes());
		String salt = userService.selectSalt(vo.getEmail());
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		
		vo.setPassword(saltingPassword);
		
		UserVO user = userService.login(vo);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		if(user != null) {
			try {
				user.setCode(rsa.decryptRSA(user.getCode()));
				String token = sessionService.createToken(user);
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", user);
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
		String salt = hashing.createSalt();
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		
		vo.setPassword(saltingPassword);
		vo.setCode(rsa.encryptRSA(vo.getCode()));
		vo.setGrade("user");
		vo.setSalt(salt);
		vo.setReg_date(new Date(System.currentTimeMillis()));
		vo.setProfile_img("normal/cat/cat.png");
		vo.setDescription("");
		vo.setReport_count(0);
		vo.setReject_end_date(new Date(System.currentTimeMillis()));
		vo.setForever_reject(false);
		vo.setReputation(50);
		
		return userService.register(vo);
	}
	
	@PostMapping("/register_code")
	public @ResponseBody String sendRegisterCode(@RequestBody UserVO vo) {
		return mailService.mailSend(vo.getEmail(), "회원가입 코드", "냥코대전쟁 정보 & 커뮤니티 사이트 회원가입 코드");
	}
	
	@PostMapping("/check_email")
	public @ResponseBody boolean checkEmail(@RequestBody UserVO vo) {
		return userService.selectEmail(vo.getEmail()) == null ? true : false;
	}
	
	@PostMapping("/check_name")
	public @ResponseBody boolean checkName(@RequestBody UserVO vo) {
		return userService.selectName(vo.getName()) == null ? true : false;
	}
	
	@PostMapping("/check_code")
	public @ResponseBody boolean checkCode(@RequestBody UserVO vo) {
		ArrayList<String> list = userService.selectCode();
		boolean check = true;
		
		for(int i = 0; i < list.size(); i++) if(rsa.decryptRSA(list.get(i)).equals(vo.getCode())) check = false;
		
		return check;
	}
	
	@PostMapping("/find_email")
	public @ResponseBody String findEmail(@RequestBody UserVO vo) {
		ArrayList<UserVO> list = userService.findEmail();
		String email = "";
		
		for(int i = 0; i < list.size(); i++) if(rsa.decryptRSA(list.get(i).getCode()).equals(vo.getCode())) email = list.get(i).getEmail();
		
		return email;
	}
	
	@PostMapping("/get_password_change_code")
	public @ResponseBody Object getPasswordChangeCode(@RequestBody UserVO vo) {
		if(userService.selectEmail(vo.getEmail()) != null) {
			ArrayList<String> list = userService.selectCode();
			boolean check = false;
			
			for(int i = 0; i < list.size(); i++) if(rsa.decryptRSA(list.get(i)).equals(vo.getCode())) check = true;
			
			if(check) {
				return mailService.mailSend(vo.getEmail(), "비밀번호 변경 코드", "냥코대전쟁 정보 & 커뮤니티 사이트 비밀번호 변경 코드");
			} else return false;
		} else return false;
	}
	
	@PostMapping("/change_password")
	public @ResponseBody int changePassword(@RequestBody UserVO vo) {
		String password = vo.getPassword();
		String digestPassword = hashing.hashing(password.getBytes());
		String salt = hashing.createSalt();
		String saltingPassword = hashing.hashing((digestPassword + salt).getBytes());
		UserVO user = new UserVO();
		user.setEmail(vo.getEmail());
		user.setPassword(saltingPassword);
		user.setSalt(salt);
		
		return userService.changePassword(user);
	}
	
	@GetMapping("/user_info/{name}")
	public @ResponseBody UserVO getUserInfo(@PathVariable String name) {
		return userService.selectUserInfo(name);
	}
	
	@PostMapping("/change_description")
	public @ResponseBody int descriptionChange(@RequestBody UserVO vo) {
		return userService.descriptionChange(vo);
	}
	
	@PostMapping("/change_name")
	public @ResponseBody int nameChange(@RequestBody UserVO vo) {
		return userService.nameChange(vo);
	}
	
	@PostMapping("/change_code")
	public @ResponseBody int codeChange(@RequestBody UserVO vo) {
		vo.setCode(rsa.encryptRSA(vo.getCode()));
		
		return userService.codeChange(vo);
	}
	
	@PostMapping("/change_profile_img")
	public @ResponseBody int profileImgChange(@RequestBody UserVO vo) {
		return userService.profileImgChange(vo);
	}
	
	@GetMapping("/user_data")
	public @ResponseBody List<UserVO> getAllUserData() {
		return userService.selectAllUserData();
	}
	
}
