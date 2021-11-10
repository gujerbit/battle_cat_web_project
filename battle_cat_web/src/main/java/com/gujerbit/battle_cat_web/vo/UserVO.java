package com.gujerbit.battle_cat_web.vo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class UserVO {

	@JsonProperty
	private String email;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String code;
	
	@JsonProperty
	private String grade;
	
	@JsonProperty
	private String salt;
	
}
