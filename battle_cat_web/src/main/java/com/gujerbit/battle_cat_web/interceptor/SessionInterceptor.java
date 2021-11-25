package com.gujerbit.battle_cat_web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gujerbit.battle_cat_web.service.SessionServiceImpl;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Autowired
	private SessionServiceImpl service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		} else {
			String token = request.getHeader("jwt-auth-token");
			
			if(token != null && token.length() > 0) {
				service.checkToken(token);
				System.out.println(token);
				return true;
			} else {
				throw new RuntimeException("no have auth-token");
			}
		}
	}
	
}
