package com.gujerbit.battle_cat_web.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class CommentVO {

	@JsonProperty
	private int idx;
	
	@JsonProperty
	private int board_idx;
	
	@JsonProperty
	private int comment_idx;
	
	@JsonProperty
	private float salt_idx;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String grade;
	
	@JsonProperty
	private String comment;
	
	@JsonProperty
	private String parent_comment;
	
	@JsonProperty
	private Timestamp comment_date;
	
	@JsonProperty
	private String profile_img;
	
}
