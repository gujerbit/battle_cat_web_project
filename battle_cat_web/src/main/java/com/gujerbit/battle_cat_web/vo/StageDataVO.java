package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StageDataVO {

	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String sortation;
	
	@JsonProperty
	private int base_hp;
	
	@JsonProperty
	private String enemy_info;
	
	@JsonProperty
	private String reward;
	
	@JsonProperty
	private int stamina;
	
	@JsonProperty
	private int stage_length;
	
	@JsonProperty
	private int max_enemys;
	
	@JsonProperty
	private String prevent;
	
}
