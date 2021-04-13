package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MemberVO {
	
	@JsonProperty
	private int id;
	
	@JsonProperty
	private String name;
	
}
