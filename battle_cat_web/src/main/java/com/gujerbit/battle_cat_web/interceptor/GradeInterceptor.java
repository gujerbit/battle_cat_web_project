package com.gujerbit.battle_cat_web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gujerbit.battle_cat_web.service.UserServiceImpl;
import com.gujerbit.battle_cat_web.vo.UserVO;

@Component
public class GradeInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getMethod().equals("OPTIONS")) return true;
		else {
			String name = request.getHeader("name");
			String grade = request.getHeader("grade");
			UserVO vo = new UserVO();
			vo.setName(name);
			
			if(service.checkGrade(vo).equals(grade)) return true;
			else throw new RuntimeException();
		}
	}
	
}
