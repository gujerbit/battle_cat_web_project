package com.gujerbit.battle_cat_web.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.util.Converter;
import com.gujerbit.battle_cat_web.util.Decoding;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Component
public class GradeInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private Decoding decoding;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getMethod().equals("OPTIONS")) return true;
		else {
			String name = URLDecoder.decode(new String(decoding.base64Decoding(request.getHeader("name").getBytes())), "utf-8");
			String grade = request.getHeader("grade");
			UserVO vo = new UserVO();
			vo.setName(name);
			
			if(service.checkGrade(vo).equals(grade)) return true;
			else throw new RuntimeException();
		}
	}
	
}
