package com.gujerbit.battle_cat_web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnitDataVO {
	// 고유 번호
	@JsonProperty
	private String id;
	// 캐릭터 이름
	@JsonProperty
	private String name;
	// 캐릭터 설명
	@JsonProperty
	private String explanation;
	// 등급
	@JsonProperty
	private String rarity;
	// 캐릭터 이미지 경로
	@JsonProperty
	private String image_code;
	// 체력
	@JsonProperty
	private int hp;
	// 히트백
	@JsonProperty
	private int hit_back;
	// 공격력
	@JsonProperty
	private int attack_power;
	// 이동 속도
	@JsonProperty
	private int move_speed;
	// 생산 속도
	@JsonProperty
	private int produce_speed;
	// 공격 속도 (선딜)
	@JsonProperty
	private int attack_speed;
	// 공격 재간격 시간 (후딜)
	@JsonProperty
	private int attack_frequency;
	// 인식 사거리
	@JsonProperty
	private int attack_range;
	// 비용
	@JsonProperty
	private int cost;
	
	@JsonProperty
	private int long_multi_range_start;
	
	@JsonProperty
	private int long_multi_range_end;
	
}
