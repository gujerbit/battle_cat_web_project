package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GachaScheduleVO {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String series;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Date start;
	
	@JsonProperty
	private Date end;
	
}
