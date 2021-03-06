package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateScheduleVO {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private String version;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Date update_date;
	
}
