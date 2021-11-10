package com.gujerbit.battle_cat_web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gujerbit.battle_cat_web.interceptor.SessionInterceptor;

@SpringBootApplication
public class BattleCatWebApplication implements WebMvcConfigurer {

	@Autowired
	private SessionInterceptor interceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(BattleCatWebApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("").excludePathPatterns(Arrays.asList("/login", "/register"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").exposedHeaders("jwt-auth-token");
	}

}
