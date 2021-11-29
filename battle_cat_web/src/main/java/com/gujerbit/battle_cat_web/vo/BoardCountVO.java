package com.gujerbit.battle_cat_web.vo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class BoardCountVO {

	@JsonProperty
	private int idx;
	
	@JsonProperty
	private int board_idx;
	
	@JsonProperty
	private String type;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String name;
	
}
