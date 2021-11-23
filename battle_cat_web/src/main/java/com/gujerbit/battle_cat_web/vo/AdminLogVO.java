package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class AdminLogVO {
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String target;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private Date log_date;
	
}
