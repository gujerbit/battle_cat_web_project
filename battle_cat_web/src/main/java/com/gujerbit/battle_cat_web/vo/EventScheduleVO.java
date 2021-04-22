package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EventScheduleVO {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Date event_start_date;
	
	@JsonProperty
	private Date event_end_date;
	
}
