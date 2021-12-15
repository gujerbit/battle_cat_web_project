package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnemyDataVO {

	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String image_dir;
	
	@JsonProperty
	private int hp;
	
	@JsonProperty
	private int hit_back;
	
	@JsonProperty
	private String attack_power;
	
	@JsonProperty
	private int move_speed;
	
	@JsonProperty
	private String attack_speed;
	
	@JsonProperty
	private int attack_end_speed;
	
	@JsonProperty
	private int attack_freq;
	
	@JsonProperty
	private String attack_range;
	
	@JsonProperty
	private int drop_money;
	
	@JsonProperty
	private String attack_type;
	
	@JsonProperty
	private String target;
	
	@JsonProperty
	private String property;
	
}
