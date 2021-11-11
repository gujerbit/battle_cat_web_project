package com.gujerbit.battle_cat_web.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
public class UserVO {

	@JsonProperty
	private String email;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String code;
	
	@JsonProperty
	private String grade;
	
	@JsonProperty
	private String salt;
	
	@JsonProperty
	private Date reg_date;
	
	@JsonProperty
	private String profile_img;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private int report_count;
	
	@JsonProperty
	private Date reject_end_date;
	
	@JsonProperty
	private boolean forever_reject;
	
	@JsonProperty
	private int reputation;
	
}
