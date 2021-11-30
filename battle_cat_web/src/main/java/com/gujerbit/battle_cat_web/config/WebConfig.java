package com.gujerbit.battle_cat_web.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.gujerbit.battle_cat_web.interceptor.GradeInterceptor;
import com.gujerbit.battle_cat_web.interceptor.SessionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private SessionInterceptor sessionInterceptor;
	
	@Autowired
	private GradeInterceptor gradeInterceptor;
	
	private final String[] EXCLUDE_PATHS = {
		"/",
		"/static/**",
		"/js/**",
		"/css/**",
		"/img/**",
		"/fonts/**",
		"/*.html",
		"/*.ico",
		"/unit_data",
		"/unitInfo/**",
		"/login",
		"/login_process",
		"/community",
		"/userInfo/**",
		"/admin",
		"/board",
		"/board_data/**",
		"/register",
		"/register_process",
		"/check_email",
		"/register_code",
		"/check_name",
		"/check_code",
		"/board_writing",
		"/board_updating/**",
		"/find_account",
		"/find_email",
		"/get_password_change_code",
		"/change_password"
	};
	
	private final String[] INCLUDE_PATHS = {
		"/user_reject",
		"/user_forever_reject",
		"/user_reject_release",
		"/user_grade_setting"
	};

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*").addResourceLocations("classpath:/static/").resourceChain(true).addResolver(new PathResourceResolver() {
			@Override
			protected Resource getResource(String resourcePath, Resource location) throws IOException {
				Resource requestedResource = location.createRelative(resourcePath);
				
				return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
			}
		});
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATHS);
		registry.addInterceptor(gradeInterceptor).addPathPatterns(INCLUDE_PATHS);
	}
	
}
