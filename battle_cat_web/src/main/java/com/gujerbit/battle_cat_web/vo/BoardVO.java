package com.gujerbit.battle_cat_web.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class BoardVO {
	
	@JsonProperty
	private int idx;
	
	@JsonProperty
	private String email;

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String grade;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String content;
	
	@JsonProperty
	private String text;
	
	@JsonProperty
	private Timestamp writing_date;
	
	@JsonProperty
	private int view_count;
	
	@JsonProperty
	private int good_count;
	
	@JsonProperty
	private int bad_count;
	
	@JsonProperty
	private boolean remove;
	
	@JsonProperty
	private String type;
	
	@JsonProperty
	private int comment_count;
	
	@JsonProperty
	private int report_count;
	
}
