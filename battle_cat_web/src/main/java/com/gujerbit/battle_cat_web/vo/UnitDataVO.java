package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnitDataVO {
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String image_dir;
	
	@JsonProperty
	private String rarity;
	
	@JsonProperty
	private int hp;
	
	@JsonProperty
	private int hit_back;
	
	@JsonProperty
	private int attack_power;
	
	@JsonProperty
	private int move_speed;
	
	@JsonProperty
	private int produce_speed;
	
	@JsonProperty
	private int attack_speed;
	
	@JsonProperty
	private int attack_freq;
	
	@JsonProperty
	private int attack_range;
	
	@JsonProperty
	private int cost;
	
	@JsonProperty
	private String attack_type;
	
	@JsonProperty
	private String target;
	
	@JsonProperty
	private String property;
	
	@JsonProperty
	private String instinct;
	
}
