package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GachaScheduleVO {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Date gacha_start_date;
	
	@JsonProperty
	private Date gacha_end_date;
	
	@JsonProperty
	private String gacha_series;
	
	@JsonProperty
	private boolean is_unconditional;
	
}
