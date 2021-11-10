package com.gujerbit.battle_cat_web.vo;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DailyScheduleVO {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Time start;
	
	@JsonProperty
	private Time end;
	
	@JsonProperty
	private int week;
	
}
