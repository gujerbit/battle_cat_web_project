package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateVersionVO {
	
	@JsonProperty
	private int id;
	
	@JsonProperty
	private String update_content;
	
	@JsonProperty
	private Date update_date;
	
}
